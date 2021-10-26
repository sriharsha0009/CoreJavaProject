package presentation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import bean.Card;
import bean.Station;
import bean.Transaction;
import exception.CardNotFoundException;
import exception.MinimumBalance;
import exception.NegativeBalanceException;
import exception.NotAddedException;
import exception.NotFoundException;
import service.CardService;
import service.CardServiceImpl;
import service.StationService;
import service.StationServiceImpl;
import service.TransactionService;
import service.TransactionServiceImpl;

public class MetroPresentationImpl implements MetroPresentation {
	
	CardService cardService = new CardServiceImpl();
	StationService stationService = new StationServiceImpl();
	TransactionService transactionService = new TransactionServiceImpl();
	

	@Override
	public void showMenu() {
		System.out.println("1. Add New Card");
		System.out.println("2. SwipeIn/Out");
		System.out.println("3. Recharge your Card");
		System.out.println("4. Exit");
	}

	@Override
	public void performMenu(int choice) {
		Scanner sc = new Scanner(System.in);
		switch(choice) {
		case 1:
			System.out.print("Enter Card Holder Name : ");
			String name = sc.next();
			
			LocalDateTime current = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
			String creationDate = current.format(format);
			
			try {
				System.out.print("Enter Balance to Load : ");
				int balance = sc.nextInt();
				if(balance<=0)
					throw new MinimumBalance("Balance should be greater than 0");
				
			
			
			Card card = new Card(1,name, balance,creationDate);
			try {
				int flag = cardService.storeCardDetails(card);
				if(flag!=0)
					System.out.println("Added Successfully and your card number is "+flag);
				else
					throw new NotAddedException("Not Added");
					//System.out.println("Not Added");
				
			}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			}catch(MinimumBalance e) {
			System.out.println("Balance should be greater than 0");
			}
			catch(NotAddedException e) {
				System.out.println("Not Added");
			}catch(InputMismatchException e) {
				System.out.println("Input not Matched Exception.");
			}
			System.out.println("=======================");
			System.out.println();
		break;
		
		case 2:
			
			try {
			perfromCard();
			}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			System.out.println("=======================");
			System.out.println();
			break;		
		case 3:
			
			try {
				System.out.print("Enter Card number : ");
				int cardId = sc.nextInt();
				boolean flag = cardService.checkCard(cardId);
				rechargeCard(cardId);
				
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}catch(CardNotFoundException e) {
				System.out.println("Card Not Found with given Number.");
			}catch(InputMismatchException e) {
				System.out.println("Input Not Matched Exception");
			}catch(NotAddedException e) {
				System.out.println("Not Added");
			}catch(NegativeBalanceException e) {
				System.out.println("Balance should be greater than 0");
			}
			System.out.println("=======================");
			System.out.println();
			break;
		case 4:
			System.out.println();
			System.out.println("Thanks...");
			System.exit(0);
		default:
			System.out.println();
			System.out.println("Enter Valid Choice");
			System.out.println("=======================");
			System.out.println();
			break;
		}

	}
	@Override
	public void perfromCard()throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your Card Number : ");
	
		int swipeIn;
		try {
		int cardNumber = sc.nextInt();
		swipeIn = transactionService.swipeInStatus(cardNumber);
		ArrayList<String> stations = stationService.getStations();
		System.out.print("Stations are ");
		for (int i = 0; i < stations.size(); i++)
            System.out.print(stations.get(i) + "  ");
		System.out.println();
		if(swipeIn<=0) {
			
			System.out.print("Enter Station Name to Swipe In : ");
			String stationName = sc.next();
			
				int stationId = stationService.checkStation(stationName);
				if(stationId==0)
					throw new NotFoundException("No station with the name");
					//System.out.println("No station with the name");
				else {
					boolean flag = transactionService.swipeInCard(cardNumber, stationId);
					if(flag)
						System.out.println("You have successfully swiped in at the station "+stationName);
					else
						System.out.println("Some Error");
				}
			
			}
		
		else {
			
			try {
				int balance2;
				boolean flag;
				
				int penality = transactionService.checkPenality(swipeIn);
				//System.out.println(penality);
				int balance=0;
				penality=penality<=-1?0:penality;
				if(penality>0) {
					System.out.println("Penality of "+penality*20+" Rs for "+penality*90+" mins.");
					
					balance = cardService.checkFare(cardNumber, penality*20);	
				}
				System.out.print("Enter Station Name to Swipe Out : ");
				String destinationStation = sc.next();
				int stationId = stationService.checkStation(destinationStation);
				if(stationId==0)
					throw new NotFoundException("No Station with the Name");
					//System.out.println("No station with the name.");
				else {
					
					int remainingBalance = stationService.swipeOutCard(cardNumber, stationId,1);
					remainingBalance=remainingBalance-penality*20;
					if(remainingBalance<0)
						
						System.out.println("No sufficient funds found, add "+Math.abs(remainingBalance)+" to continue.");
					else {
						if(penality!=0)
							flag= transactionService.updatePenality(swipeIn,penality*20);
						balance2=stationService.swipeOutCard(cardNumber, stationId, 2);
						balance2 = cardService.updateCardBalance(cardNumber, remainingBalance);
						Transaction transaction = new Transaction();
						transaction=transactionService.displayDetails(swipeIn);
						System.out.println("Your Travel details is from L"+transaction.getSourceStationId()+" at "+transaction.getSwipeInTime()+" to L"+transaction.getDestinationStationId()+" at "+transaction.getSwipeOutTime());
						System.out.println("Your Total Fare is "+transaction.getFare()+" with penality of "+penality*20);
					System.out.println("You have successfully Swiped Out with card balance as "+remainingBalance);
				}
				}
				
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
		}
		}catch(MinimumBalance m) {
			System.out.println(m);
			}catch(CardNotFoundException e) {
				System.out.println("Card Not Found with given number.");
			}
		catch(InputMismatchException e) {
			System.out.println("Input Not Matched Exception");
		}
		catch(NotFoundException e) {
			System.out.println("Station Not Found with given name.");
		}
	}

	@Override
	public void rechargeCard(int cardId) throws ClassNotFoundException, SQLException {
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter Recharge Amount : ");
		int amount = sc.nextInt();
		if(amount<=0)
			throw new NegativeBalanceException("Negative Balance cannot be added.");
		int allAmount = cardService.rechargeCard(cardId,amount);
		if(allAmount>0)
			System.out.println("Final amount after adding is "+allAmount);
		else
			throw new NotAddedException("Not Added");
			//System.out.println("Not Added");
	}
}
			


