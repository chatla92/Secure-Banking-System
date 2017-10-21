package hibernateModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "creditcards")
public class CreditCards {

	public CreditCards(String cardNo, Accounts accId, int cvv, float outstandingamount) {
		this.card_no = cardNo;
		this.accId = accId;
		this.cvv = cvv;
		this.outstandingamount = outstandingamount;
	}

	public CreditCards() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "card_no", nullable = false)
	private String card_no;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acc_id", nullable = false)
	private Accounts accId;

	@Column(name = "cvv", nullable = false)
	private int cvv;

	@Column(name = "isActive", nullable = false)
	private boolean isActive;

	@Column(name = "maxlimit", nullable = false)
	private int maxlimit;

	@Column(name = "outstandingamount", nullable = false)
	private float outstandingamount;

	@Column(name = "dueDate", nullable = false)
	private int dueDate;

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public Accounts getAccId() {
		return accId;
	}

	public void setAccId(Accounts accId) {
		this.accId = accId;
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

	public int getMaxlimit() {
		return maxlimit;
	}

	public void setMaxlimit(int maxlimit) {
		this.maxlimit = maxlimit;
	}

	public float getOutstandingamount() {
		return outstandingamount;
	}

	public void setOutstandingamount(float outstandingamount) {
		this.outstandingamount = outstandingamount;
	}

	public int getDueDate() {
		return dueDate;
	}

	public void setDueDate(int dueDate) {
		this.dueDate = dueDate;
	}

}
