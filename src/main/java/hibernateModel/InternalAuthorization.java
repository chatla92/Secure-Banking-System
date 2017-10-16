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
@Table(name = "Pending_Auth")
public class InternalAuthorization {

	public InternalAuthorization() {

	}

	public InternalAuthorization(int id, Transaction transaction, InternalUser intUser, boolean auth_state) {
		this.id = id;
		this.transaction = transaction;
		this.intUser = intUser;
		this.auth_state = auth_state;
	}

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "trans_id", nullable = false)
	private Transaction transaction;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", nullable = false)
	private InternalUser intUser;

	@Column(name = "auth_state", nullable = false)
	private boolean auth_state;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public InternalUser getIntUser() {
		return intUser;
	}

	public void setIntUser(InternalUser intUser) {
		this.intUser = intUser;
	}

	public boolean isAuth_state() {
		return auth_state;
	}

	public void setAuth_state(boolean auth_state) {
		this.auth_state = auth_state;
	}
}
