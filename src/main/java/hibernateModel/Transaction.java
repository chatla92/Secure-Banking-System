package hibernateModel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "transactions")
public class Transaction {

	public Transaction(float amount, String type, String from_acc, String to_acc, boolean iscritical,
			boolean transactionStatus, int init_id) {
		super();
		this.amount = amount;
		this.type = type;
		this.from_acc = from_acc;
		this.to_acc = to_acc;
		this.iscritical = iscritical;
		this.transactionStatus = transactionStatus;
		this.init_id = init_id;
	}

	public Transaction() {

	}

	@Id
	@Column(name = "trans_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trans_id;

	@Column(name = "amount", nullable = false)
	private float amount;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "from_acc", nullable = false)
	private String from_acc;

	@Column(name = "to_acc", nullable = false)
	private String to_acc;

	@Column(name = "iscritical", nullable = false)
	private boolean iscritical;

	@Column(name = "trans_status", nullable = false)
	private boolean transactionStatus;

	@Column(name = "approvedBy", nullable = false)
	private int internalUser;

	@Column(name = "init_id", nullable = false)
	private int init_id;

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<InternalAuthorization> internalAuth;

	@OneToMany(mappedBy = "transactions", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<ExternalAuthorization> externalAuth;

	public int getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom_acc() {
		return from_acc;
	}

	public void setFrom_acc(String from_acc) {
		this.from_acc = from_acc;
	}

	public String getTo_acc() {
		return to_acc;
	}

	public void setTo_acc(String to_acc) {
		this.to_acc = to_acc;
	}

	public boolean isIscritical() {
		return iscritical;
	}

	public void setIscritical(boolean iscritical) {
		this.iscritical = iscritical;
	}

	public boolean isTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(boolean transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public int getInternalUser() {
		return internalUser;
	}

	public void setInternalUser(int internalUser) {
		this.internalUser = internalUser;
	}

	public int getInit_id() {
		return init_id;
	}

	public void setInit_id(int init_id) {
		this.init_id = init_id;
	}

	public Set<InternalAuthorization> getInternalAuth() {
		return internalAuth;
	}

	public void setInternalAuth(Set<InternalAuthorization> internalAuth) {
		this.internalAuth = internalAuth;
	}

	public Set<ExternalAuthorization> getExternalAuth() {
		return externalAuth;
	}

	public void setExternalAuth(Set<ExternalAuthorization> externalAuth) {
		this.externalAuth = externalAuth;
	}

}
