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
@Table(name = "pending_auth")
public class InternalAuthorization {

	public InternalAuthorization() {

	}

	public InternalAuthorization(Transaction transaction, String role, boolean auth_state) {
		this.transaction = transaction;
		this.role = role;
		this.auth_state = auth_state;
	}

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "trans_id", nullable = false)
	private Transaction transaction;

	@Column(name = "role", nullable = false)
	private String role;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public boolean isAuth_state() {
		return auth_state;
	}

	public void setAuth_state(boolean auth_state) {
		this.auth_state = auth_state;
	}

}
