package hibernateModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pending_auth_ext")
public class ExternalAuthorization {

	public ExternalAuthorization() {

	}

	public ExternalAuthorization(Transaction transactions, Accounts account, boolean isAuth) {
		this.transactions = transactions;
		this.account = account;
		this.isAuth = isAuth;
	}

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "trans_id", nullable = false)
	private Transaction transactions;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acc_id", nullable = false)
	private Accounts account;

	@Column(name = "isAuth", nullable = false)
	private boolean isAuth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Transaction getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction transactions) {
		this.transactions = transactions;
	}

	public Accounts getAccount() {
		return account;
	}

	public void setAccount(Accounts account) {
		this.account = account;
	}

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

}
