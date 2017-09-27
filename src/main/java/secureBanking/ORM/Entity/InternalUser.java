package secureBanking.ORM.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="internal_users")
public class InternalUser {

	@Id
	int employee_id;
    String priv;
    String email;
    String phoneNo;
    private long salary;
    private boolean isActive;

    public InternalUser(){};
	public InternalUser(int id,String email)
    {
        this.employee_id=id;
        this.email=email;
        this.priv="priv";
        this.phoneNo="phone";
        this.salary=9000;
        this.isActive=true;

    }
}
