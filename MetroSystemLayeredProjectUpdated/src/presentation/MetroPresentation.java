package presentation;

import java.sql.SQLException;

public interface MetroPresentation {

	public void showMenu();
	
	public void performMenu(int choice);
	
	public void perfromCard()throws ClassNotFoundException, SQLException;
	
	public void rechargeCard(int cardId)throws ClassNotFoundException, SQLException;
}
