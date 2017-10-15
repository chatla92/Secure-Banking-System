package hibernateModel;

import java.util.List;

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
@Table(name = "Pending_Auth_Ext")
public class ExternalAuthorization {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public boolean isAuth() {
		return isAuth;
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

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

}
