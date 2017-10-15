package hibernateModel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Accounts {

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

	@OneToMany(mappedBy = "accounts", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Cards> cards;

	public List<Cards> getCards() {
		return cards;
	}

	public void setCards(List<Cards> cards) {
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
