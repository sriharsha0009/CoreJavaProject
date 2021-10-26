package bean;

public class Card {

	private int cardId;
	private int balance;
	private String creationDate, holderName;
	public Card(int cardId,String holderName, int balance, String creationDate) {
		super();
		this.cardId = cardId;
		this.holderName=holderName;
		this.balance = balance;
		this.creationDate = creationDate;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", balance=" + balance + ", creationDate=" + creationDate + ", holderName="
				+ holderName + "]";
	}
	
	
	

}
