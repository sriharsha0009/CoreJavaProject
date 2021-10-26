package service;

import java.sql.SQLException;

import bean.Transaction;

public interface TransactionService {

	int swipeInStatus(int cardId)throws ClassNotFoundException, SQLException;
	boolean swipeInCard(int cardId, int stationId) throws SQLException, ClassNotFoundException;
	int[] swipeOut(int cardId,int stationId)throws SQLException, ClassNotFoundException;
	boolean swipeOutUpdate(int stationId,int transactionId, int fare) throws SQLException, ClassNotFoundException;
	int checkPenality(int transactionId) throws SQLException, ClassNotFoundException;
	boolean updatePenality(int swipeIn, int i) throws ClassNotFoundException, SQLException;
	Transaction displayDetails(int transactionId)throws ClassNotFoundException, SQLException;
}
