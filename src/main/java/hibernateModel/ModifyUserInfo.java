package hibernateModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modify_user_info")
public class ModifyUserInfo {

	public ModifyUserInfo(String email, String address, String zipcode, String gender, String contact_no,
			String user_name) {
		super();
		this.email = email;
		this.address = address;
		this.zipcode = zipcode;
		this.gender = gender;
		this.contact_no = contact_no;
		this.user_name = user_name;
	}

	ModifyUserInfo() {

	}

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "zipcode", nullable = false)
	private String zipcode;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "contact_no", nullable = false)
	private String contact_no;

	@Column(name = "user_name", nullable = false)
	private String user_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
