package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Card;
import bean.Station;

public interface StationDao {

	//boolean swipeIn(int card, int station) throws SQLException, ClassNotFoundException;
	int checkStation(String stationName) throws ClassNotFoundException, SQLException;
	//int swipeOut(int cardId, int stationId)throws SQLException, ClassNotFoundException;
	//boolean swipeOutUpdate(int stationId, int cardId, int fare )throws SQLException, ClassNotFoundException;
	//int updateFare(int cardId, int fare)throws SQLException, ClassNotFoundException;
	int fareCalculate(int sourceStationId, int stationId)throws SQLException, ClassNotFoundException;
	//int updateCardBalance(int cardId, int amount)throws SQLException, ClassNotFoundException;
	
	ArrayList<String> getAllStations()throws SQLException, ClassNotFoundException;
}
