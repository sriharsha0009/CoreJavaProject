package persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bean.Transaction;

public class TransactionDaoImpl implements TransactionDao {

	CardDao cardDao = new CardDaoImpl();
	
	@Override
	public int swipeInStatus(int cardId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Transaction WHERE CardId =? AND DestinationStationId=?");
		boolean cardCheck = cardDao.checkCardDetails(cardId,2);
		preparedStatement.setInt(1, cardId);
		preparedStatement.setInt(2, 0);
		ResultSet resultSet=preparedStatement.executeQuery();
		int transactionId=0;
		while(resultSet.next())
			transactionId=resultSet.getInt("TransactionId");
		connection.close();
		 return transactionId;
	}
	
	@Override
	public boolean insertSwipeIn(int cardId, int stationId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		
		PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO TRANSACTION(CARDID,SOURCESTATIONID,SWIPEINTIME) VALUES(?,?,?)");
		preparedStatement.setInt(1,cardId);
		preparedStatement.setInt(2, stationId);
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		String formatedDateTime = current.format(format);
		preparedStatement.setString(3, formatedDateTime);
		int flag = preparedStatement.executeUpdate();
		connection.close();
		if(flag>0)
			return true;
		else return false;
	}
	
	
	public int[] swipeOut(int cardId, int stationId) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Transaction WHERE CardId =? AND DESTINATIONSTATIONID=?");
		preparedStatement.setInt(1, cardId);
		preparedStatement.setInt(2, 0);
		ResultSet resultSet=preparedStatement.executeQuery();
		int sourceStationId =0,transactionId =0;
		while(resultSet.next()) {
			sourceStationId=resultSet.getInt("SourceStationId");
			transactionId =resultSet.getInt("TransactionId");
		}
		int[] details = new int[] {sourceStationId,transactionId};
		connection.close();
		return details;
	}
	
	
	@Override
	public boolean swipeOutUpdate(int destinationId,int transactionId, int fare) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement2=connection.prepareStatement("UPDATE Transaction SET DestinationStationId = ?,SwipeOutTime=?,Fare=? WHERE TransactionId =?");
		preparedStatement2.setInt(1, destinationId);
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
		String currentDate = current.format(format);
		preparedStatement2.setString(2, currentDate);
		preparedStatement2.setInt(3, fare);
		preparedStatement2.setInt(4, transactionId);
		int flag = preparedStatement2.executeUpdate();
		connection.close();
		if(flag>0)
			return true;
		return false;
	}
	
	public String checkPenality(int transactionId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement2=connection.prepareStatement("SELECT * FROM Transaction where TransactionId = ?");
		preparedStatement2.setInt(1,transactionId);
		ResultSet resultSet = preparedStatement2.executeQuery();
		String swipeInTime="";
		while(resultSet.next()) 
			swipeInTime = resultSet.getString("SwipeInTime");
		
		connection.close();
		return swipeInTime;
	}

	@Override
	public boolean updatePenality(int transactionId, int amount) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement2=connection.prepareStatement("UPDATE Transaction SET Penality = ? WHERE TransactionId =?");
		preparedStatement2.setInt(1, amount);
		preparedStatement2.setInt(2, transactionId);
		int flag=preparedStatement2.executeUpdate();
		connection.close();
		if(flag>0)
			return true;
		return false;
	}


	@Override
	public Transaction displayDetails(int transactionId) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Transaction WHERE TransactionId=?");
		preparedStatement.setInt(1, transactionId);
		ResultSet resultSet= preparedStatement.executeQuery();
		Transaction transaction = new Transaction();
		while(resultSet.next()) {
			transaction.setDestinationStationId(resultSet.getInt("DestinationStationId"));
			transaction.setSourceStationId(resultSet.getInt("SourceStationId"));
			transaction.setFare(resultSet.getInt("Fare"));
			transaction.setSwipeInTime(resultSet.getString("SwipeInTime"));
			transaction.setSwipeOutTime(resultSet.getString("SwipeOutTime"));
		}
		return transaction;
	}


}
