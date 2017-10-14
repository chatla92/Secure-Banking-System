package model;

public class Cards {
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	private String card_no;
	private String type;
	private String acc_id;
	private int cvv;
	private boolean isActive;
	
}
