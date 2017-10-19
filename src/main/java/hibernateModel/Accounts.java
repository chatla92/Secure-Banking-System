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
@Table(name = "Accounts")
public class Accounts {

	public Accounts() {

	}

	public Accounts(String acc_id, String type, long balance, ExternalUser extUser, Set<Cards> cards) {
		this.acc_id = acc_id;
		this.type = type;
		this.balance = balance;
		this.extUser = extUser;
		this.cards = cards;
	}

	@Id
	@Column(name = "acc_id", nullable = false)
	private String acc_id;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "balance", nullable = false)
	private long balance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private ExternalUser extUser;

	@OneToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Cards> cards;

	public Set<Cards> getCards() {
		return cards;
	}

	public void setCards(Set<Cards> cards) {
		this.cards = cards;
	}

	public String getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public ExternalUser getExtUser() {
		return extUser;
	}

	public void setExtUser(ExternalUser extUser) {
		this.extUser = extUser;
	}
}
