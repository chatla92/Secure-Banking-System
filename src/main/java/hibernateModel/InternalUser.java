package hibernateModel;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "internal_users")
public class InternalUser {
	@Id
	@Column(name = "emp_id", nullable = false)
	private int emp_id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "ssn", nullable = false)
	private String ssn;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "zipcode", nullable = false)
	private String zipcode;
	@Column(name = "gender", nullable = false)
	private String gender;
	@Column(name = "user_name", nullable = false)
	private String user_name;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "contact_no", nullable = false)
	private String contact_no;
	@Column(name = "priv", nullable = false)
	private String priv;
	@Column(name = "salary", nullable = false)
	private long salary;
	@Column(name = "threshold", nullable = false)
	private long threshold;

	@OneToMany(mappedBy = "intUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<InternalAuthorization> internalAuth;

	@OneToMany(mappedBy = "internalUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getPriv() {
		return priv;
	}

	public void setPriv(String priv) {
		this.priv = priv;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public long getThreshold() {
		return threshold;
	}

	public void setThreshold(long threshold) {
		this.threshold = threshold;
	}

	public List<InternalAuthorization> getInternalAuth() {
		return internalAuth;
	}

	public void setInternalAuth(List<InternalAuthorization> internalAuth) {
		this.internalAuth = internalAuth;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
