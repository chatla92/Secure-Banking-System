package hibernateModel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "accounts")
public class Accounts {

	public Accounts() {

	}

	public Accounts(String acc_id, String type, float balance, ExternalUser extUser) {
		this.acc_id = acc_id;
		this.type = type;
		this.balance = balance;
		this.extUser = extUser;
	}

	@Id
	@Column(name = "acc_id", nullable = false)
	private String acc_id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "balance", nullable = false)
	private float balance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private ExternalUser extUser;

	@OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<DebitCards> debitCards;

	@OneToMany(mappedBy = "accId", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<CreditCards> creditCards;

	public String getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(String accId) {
		this.acc_id = accId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public ExternalUser getExtUser() {
		return extUser;
	}

	public void setExtUser(ExternalUser extUser) {
		this.extUser = extUser;
	}

	public Set<DebitCards> getDebitCards() {
		return debitCards;
	}

	public void setDebitCards(Set<DebitCards> debitCards) {
		this.debitCards = debitCards;
	}

	public Set<CreditCards> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(Set<CreditCards> creditCards) {
		this.creditCards = creditCards;
	}

}
