package service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bean.Transaction;
import persistence.TransactionDao;
import persistence.TransactionDaoImpl;

public class TransactionServiceImpl implements TransactionService {

	private TransactionDao transactionDao = new TransactionDaoImpl();

	@Override
	public int swipeInStatus(int cardId) throws ClassNotFoundException, SQLException {
		return transactionDao.swipeInStatus(cardId);
	}
	
	@Override
	public boolean swipeInCard(int cardId, int stationId) throws SQLException, ClassNotFoundException {
			
			return transactionDao.insertSwipeIn(cardId, stationId);
	}

	@Override
	public int[] swipeOut(int cardId, int stationId) throws SQLException, ClassNotFoundException {
		
		return transactionDao.swipeOut(cardId, stationId);
	}

	@Override
	public boolean swipeOutUpdate(int stationId, int transactionId, int fare)
			throws SQLException, ClassNotFoundException {
		
		return transactionDao.swipeOutUpdate(stationId,transactionId, fare);
	}

	@Override
	public int checkPenality(int transactionId) throws SQLException, ClassNotFoundException {
		String timeDetails = transactionDao.checkPenality(transactionId);
		String[] swipeInTime =timeDetails.split(" ",5);
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		String currentDate = current.format(format);
		String[] swipeOutTime =currentDate.split(" ",5);
		//System.out.println();
		if(swipeInTime[0].equals(swipeOutTime[0])) {
			String[] timeIn = swipeInTime[1].split(":",5);
			String[] timeOut = swipeOutTime[1].split(":",5);
			int hour = Integer.parseInt(timeOut[0])-Integer.parseInt(timeIn[0]);
			int min = Integer.parseInt(timeOut[1])-Integer.parseInt(timeIn[1]);
			//System.out.println(min+" "+hour);
			int penality = hour*60+min;
			penality = penality/90;
			//System.out.println(penality);
			return penality;
		}
		return 0;
		
	}

	@Override
	public boolean updatePenality(int transactionId, int amount) throws ClassNotFoundException, SQLException {
		return transactionDao.updatePenality(transactionId, amount);
		
	}


	@Override
	public Transaction displayDetails(int transactionId) throws ClassNotFoundException, SQLException {
		return transactionDao.displayDetails(transactionId);
	}
	
	
	
}
