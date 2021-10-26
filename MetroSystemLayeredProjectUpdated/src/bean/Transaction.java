package bean;

public class Transaction {

	private int transacationId,cardId,SourceStationId, DestinationStationId;
	private String swipeInTime,swipeOutTime;
	private int fare, penality;
	public Transaction() {
		
	}
	public Transaction(int transacationId, int cardId, int sourceStationId, int destinationStationId,
			String swipeInTime, String swipeOutTime, int fare, int penality) {
		super();
		this.transacationId = transacationId;
		this.cardId = cardId;
		SourceStationId = sourceStationId;
		DestinationStationId = destinationStationId;
		this.swipeInTime = swipeInTime;
		this.swipeOutTime = swipeOutTime;
		this.fare = fare;
		this.penality = penality;
	}
	public int getTransacationId() {
		return transacationId;
	}
	public void setTransacationId(int transacationId) {
		this.transacationId = transacationId;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getSourceStationId() {
		return SourceStationId;
	}
	public void setSourceStationId(int sourceStationId) {
		SourceStationId = sourceStationId;
	}
	public int getDestinationStationId() {
		return DestinationStationId;
	}
	public void setDestinationStationId(int destinationStationId) {
		DestinationStationId = destinationStationId;
	}
	public String getSwipeInTime() {
		return swipeInTime;
	}
	public void setSwipeInTime(String swipeInTime) {
		this.swipeInTime = swipeInTime;
	}
	public String getSwipeOutTime() {
		return swipeOutTime;
	}
	public void setSwipeOutTime(String swipeOutTime) {
		this.swipeOutTime = swipeOutTime;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public int getPenality() {
		return penality;
	}
	public void setPenality(int penality) {
		this.penality = penality;
	}
	@Override
	public String toString() {
		return "Transaction [transacationId=" + transacationId + ", cardId=" + cardId + ", SourceStationId="
				+ SourceStationId + ", DestinationStationId=" + DestinationStationId + ", swipeInTime=" + swipeInTime
				+ ", swipeOutTime=" + swipeOutTime + ", fare=" + fare + ", penality=" + penality + "]";
	}
	
	
	
}
