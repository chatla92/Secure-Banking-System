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
@Table(name = "external_users")
public class ExternalUser {

	public ExternalUser() {

	}

	public ExternalUser(int user_id, String name, String ssn, String email, String address, String zipcode,
			String gender, String user_name, String contact_no, String password, String role, long threshold,
			List<Accounts> accounts) {
		this.user_id = user_id;
		this.name = name;
		this.ssn = ssn;
		this.email = email;
		this.address = address;
		this.zipcode = zipcode;
		this.gender = gender;
		this.user_name = user_name;
		this.contact_no = contact_no;
		this.password = password;
		this.role = role;
		this.threshold = threshold;
		this.accounts = accounts;
	}

	@Id
	@Column(name = "user_id", nullable = false)
	private int user_id;

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

	@Column(name = "contact_no", nullable = false)
	private String contact_no;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "threshold", nullable = false)
	private long threshold;

	@OneToMany(mappedBy = "extUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Accounts> accounts;

	public List<Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getThreshold() {
		return threshold;
	}

	public void setThreshold(long threshold) {
		this.threshold = threshold;
	}

}
