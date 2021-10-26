package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StationDaoImpl implements StationDao {
	

	@Override
	public int checkStation(String stationName) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		
		PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM StationsInformation WHERE StationName = ?");
		preparedStatement.setString(1,stationName);
		ResultSet resultSet=preparedStatement.executeQuery();
		int stationId =0;
		while(resultSet.next()) {
			stationId=resultSet.getInt("StationId");
		}
		connection.close();
		return stationId;
	}	
	@Override
	public int fareCalculate(int sourceStationId, int stationId) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement1=connection.prepareStatement("SELECT * FROM StationsInformation WHERE StationId IN(?,?)");
		preparedStatement1.setInt(1, sourceStationId);
		preparedStatement1.setInt(2, stationId);
		ResultSet resultSet1=preparedStatement1.executeQuery();
		int[] priority = new int[2];
		int i=0;
		while(resultSet1.next())
			priority[i++]=resultSet1.getInt("Priority");
		int fare =(priority[0]>priority[1]) ? (priority[0]-priority[1])*5 : (priority[1]-priority[0])*5;
		if(sourceStationId==stationId)
			fare=0;
		connection.close();
		return fare;
	}


	@Override
	public ArrayList<String> getAllStations() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/project", "root", "");
		PreparedStatement preparedStatement1=connection.prepareStatement("SELECT * FROM StationsInformation");
		ResultSet resultSet = preparedStatement1.executeQuery();
		ArrayList<String> stations = new ArrayList<String>();
		while(resultSet.next())
			stations.add(resultSet.getString("StationName"));	
		connection.close();
		return stations;
	}

}
