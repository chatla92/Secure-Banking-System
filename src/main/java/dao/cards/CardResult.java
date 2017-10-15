package dao.cards;

public class CardResult {
	private String card_no, type, acc_type;
	long balance;

	public CardResult(String card_no, String type, long balance, String acc_type)
	{
		this.card_no=card_no;
		this.type=type;
		this.balance=balance;
		this.acc_type=acc_type;
	}
	
	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	
}

