package persistence;

import java.sql.SQLException;

import bean.Card;

public interface CardDao {
	int storeDetails(Card passenger) throws ClassNotFoundException, SQLException;
//	boolean swipeIn(int cardId) throws ClassNotFoundException, SQLException;
	
	int rechargeCard(int cardId, int addAmount) throws ClassNotFoundException, SQLException;
	boolean checkCardDetails(int cardId,int choice) throws ClassNotFoundException, SQLException;
	int updateAmount(int cardId, int amount) throws ClassNotFoundException, SQLException;
	int returnCardId()throws ClassNotFoundException, SQLException;
	int updateFare(int cardId, int fare) throws SQLException, ClassNotFoundException;
	int updateCardBalance(int cardId, int amount) throws SQLException, ClassNotFoundException;

}
