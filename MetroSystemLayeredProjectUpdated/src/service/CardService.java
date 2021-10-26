package service;

import java.sql.SQLException;

import bean.Card;

public interface CardService {

	int storeCardDetails(Card passenger) throws ClassNotFoundException, SQLException;
	//boolean getSwipeInStatus(int cardId)throws ClassNotFoundException, SQLException;
	int rechargeCard(int cardId,int amount) throws ClassNotFoundException, SQLException;
	boolean checkCard(int cardId) throws ClassNotFoundException, SQLException;
	int updateFare(int cardId, int fare)throws ClassNotFoundException, SQLException;
	int checkFare(int cardNumber, int i)throws ClassNotFoundException, SQLException;
	int updateCardBalance(int cardNumber, int amount)throws ClassNotFoundException, SQLException;
}
