package persistence;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Card;
import exception.CardNotFoundException;
import exception.MinimumBalance;

public class CardDaoImpl implements CardDao {

	@Override
	public int storeDetails(Card card) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		
		PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO Card(CardHolderName, CreationDate, Balance) VALUES(?,?,?)");
		preparedStatement.setString(1,card.getHolderName());
		preparedStatement.setString(2,card.getCreationDate());
		preparedStatement.setInt(3,card.getBalance());
		
		int flag = preparedStatement.executeUpdate();
		connection.close();
		if(flag>0) {
			return returnCardId();
		}
		else
			return 0;
	}
	
	@Override
	public int returnCardId() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement1=connection.prepareStatement("SELECT * from card");
		ResultSet cardId = preparedStatement1.executeQuery();
		int cardId1 = 0;
		while(cardId.next()) {
			cardId1 = cardId.getInt("cardId");
		}
		connection.close();
		return cardId1;
	}

	

	@Override
	public int rechargeCard(int cardId,int addAmount) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Card WHERE CardId =?");
		preparedStatement.setInt(1, cardId);
		ResultSet resultSet=preparedStatement.executeQuery();
		int amount =-1;
		while(resultSet.next())
			amount = resultSet.getInt("Balance");	
		amount+=addAmount;
		connection.close();
		return updateAmount(cardId, amount);
		
	}

	@Override
	public boolean checkCardDetails(int cardId,int choice) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM Card WHERE CardId =?");
		preparedStatement.setInt(1, cardId);
		ResultSet resultSet=preparedStatement.executeQuery();
		int amount =-1;
		while(resultSet.next())
			amount = resultSet.getInt("Balance");
		connection.close();
		if(amount==-1) 
			throw new CardNotFoundException("No Card with this number");
		if(amount<20 && choice ==2)
			throw new MinimumBalance("Balance is below 20.");
		return true;
		
	}

	@Override
	public int updateAmount(int cardId, int amount) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement1=connection.prepareStatement("UPDATE Card SET Balance = ? WHERE CardId =?");
		preparedStatement1.setInt(1, amount);
		preparedStatement1.setInt(2, cardId);
		int flag = preparedStatement1.executeUpdate();
		connection.close();
		if(flag>0)
			return amount;
		return 0;
	}

	@Override
	public int updateFare(int cardId, int fare) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement1=connection.prepareStatement("SELECT * FROM Card WHERE CardId =?");
		preparedStatement1.setInt(1, cardId);
		ResultSet resultSet=preparedStatement1.executeQuery();
		int amount=0;
		while(resultSet.next())
			amount = resultSet.getInt("Balance");
		amount =amount-fare;
		//int[] details = new int[] {cardId,amount};
		connection.close();
		return amount;
	}

	@Override
	public int updateCardBalance(int cardId, int amount) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement2=connection.prepareStatement("UPDATE Card SET Balance = ? WHERE CardId =?");
		preparedStatement2.setInt(1, amount);
		preparedStatement2.setInt(2, cardId);
		int flag1 = preparedStatement2.executeUpdate();
		connection.close();
		if(flag1>0)
			return amount;
		return 0;
	}
	
	
}
