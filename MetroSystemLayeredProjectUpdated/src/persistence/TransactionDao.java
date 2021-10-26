package persistence;

import java.sql.SQLException;

import bean.Transaction;

public interface TransactionDao {

	boolean insertSwipeIn(int cardId, int stationId) throws ClassNotFoundException, SQLException;
	int[] swipeOut(int cardId, int stationId) throws SQLException, ClassNotFoundException;
	boolean swipeOutUpdate(int destinationId,int transactionId, int fare) throws SQLException, ClassNotFoundException;
	int swipeInStatus(int cardId) throws ClassNotFoundException, SQLException;
	String checkPenality(int transactionId) throws SQLException, ClassNotFoundException;
	boolean updatePenality(int transactionId, int amount) throws ClassNotFoundException, SQLException;
Transaction displayDetails(int transactionId)throws ClassNotFoundException, SQLException;
}
