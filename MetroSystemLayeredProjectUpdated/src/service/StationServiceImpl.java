package service;

import java.sql.SQLException;
import java.util.ArrayList;

import bean.Card;
import bean.Station;
import persistence.StationDao;
import persistence.StationDaoImpl;
import persistence.TransactionDao;
import persistence.TransactionDaoImpl;

public class StationServiceImpl implements StationService {

	StationDao stationDao = new StationDaoImpl();
	TransactionService transactionService = new TransactionServiceImpl();
	CardService cardService = new CardServiceImpl();

	@Override
	public int checkStation(String stationName) throws ClassNotFoundException, SQLException {
		return stationDao.checkStation(stationName);
	}

	@Override
	public int swipeOutCard(int cardId, int stationId,int choice) throws SQLException, ClassNotFoundException {
		int[] details =  transactionService.swipeOut(cardId, stationId);
		int fare = stationDao.fareCalculate(details[0], stationId);
		int fareTotal = cardService.updateFare(cardId, fare);
		if(fareTotal>0 && choice==2) {				
			if(transactionService.swipeOutUpdate(stationId,details[1], fare))				
				return fareTotal;
		}
		return fareTotal;
	}

	@Override
	public ArrayList<String> getStations() throws SQLException, ClassNotFoundException {
		return stationDao.getAllStations();
	}

}
