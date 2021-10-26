package bean;

public class Station {

	
	private int stationId;
	private String stationName;
	private int priority;
	public Station(int stationId, String stationName, int priority) {
		super();
		this.stationId = stationId;
		this.stationName = stationName;
		this.priority = priority;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", stationName=" + stationName + ", priority=" + priority + "]";
	}
	
	
}
