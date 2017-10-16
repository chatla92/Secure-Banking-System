package hibernateModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cards")
public class Cards {

	public Cards() {

	}

	public Cards(String card_no, String type, Accounts accounts, int cvv, boolean isActive) {
		this.card_no = card_no;
		this.type = type;
		this.accounts = accounts;
		this.cvv = cvv;
		this.isActive = isActive;
	}

	@Id
	@Column(name = "card_no", nullable = false)
	private String card_no;

	@Column(name = "type", nullable = false)
	private String type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acc_id", nullable = false)
	private Accounts accounts;

	@Column(name = "cvv", nullable = false)
	private int cvv;

	@Column(name = "isActive", nullable = false)
	private boolean isActive;

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

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
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

}
