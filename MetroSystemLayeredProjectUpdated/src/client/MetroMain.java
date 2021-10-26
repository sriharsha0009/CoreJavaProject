package client;

import java.util.Scanner;


import presentation.MetroPresentation;
import presentation.MetroPresentationImpl;

public class MetroMain {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		MetroPresentation metroPresentation=new MetroPresentationImpl();
		
		while(true) {
			metroPresentation.showMenu();
			System.out.print("Enter Your choice: ");
			int choice=scanner.nextInt();
			metroPresentation.performMenu(choice);
		}
	}

}
