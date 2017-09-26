package secureBanking.orm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="internal_users")
public class InternalUser {

	@Id
	@Column(name = "employee_id")
	private int id;

	@Column(name = "priv")
	private String priv;

	@Column(name = "email")
	String email;

	@Column(name = "phone_no")
	String phoneNo;

	@Column(name = "salary")
	private long salary;

	@Column(name = "isActive")
	private boolean isActive;
}
