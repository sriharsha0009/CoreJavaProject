package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Card;
import bean.Station;

public interface StationService {

	//boolean swipeInCard(int passengerID, int stationId) throws SQLException, ClassNotFoundException;
	int checkStation(String stationName) throws ClassNotFoundException, SQLException;
	int swipeOutCard(int passengerId, int stationId, int choice)throws SQLException, ClassNotFoundException;
	
	ArrayList<String> getStations()throws SQLException, ClassNotFoundException;
}
