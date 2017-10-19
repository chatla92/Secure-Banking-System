package hibernateModel;

import java.util.List;
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
@Table(name = "transactions")
public class Transaction {

	public Transaction() {

	}

	public Transaction(int trans_id, long amount, String type, String from_acc, String to_acc, boolean isActive,
			InternalUser internalUser, int init_id, Set<InternalAuthorization> internalAuth,
			Set<ExternalAuthorization> externalAuth) {
		this.trans_id = trans_id;
		this.amount = amount;
		this.type = type;
		this.from_acc = from_acc;
		this.to_acc = to_acc;
		this.isActive = isActive;
		this.internalUser = internalUser;
		this.init_id = init_id;
		this.internalAuth = internalAuth;
		this.externalAuth = externalAuth;
	}

	@Id
	@Column(name = "trans_id", nullable = false)
	private int trans_id;

	@Column(name = "amount", nullable = false)
	private long amount;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "from_acc", nullable = false)
	private String from_acc;

	@Column(name = "to_acc", nullable = false)
	private String to_acc;

	@Column(name = "isActive", nullable = false)
	private boolean isActive;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", nullable = false)
	private InternalUser internalUser;

	@Column(name = "init_id", nullable = false)
	private int init_id;

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<InternalAuthorization> internalAuth;

	@OneToMany(mappedBy = "transactions", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<ExternalAuthorization> externalAuth;

	public InternalUser getInternalUser() {
		return internalUser;
	}

	public void setInternalUser(InternalUser internalUser) {
		this.internalUser = internalUser;
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

	public int getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getInit_id() {
		return init_id;
	}

	public void setInit_id(int init_id) {
		this.init_id = init_id;
	}
}
