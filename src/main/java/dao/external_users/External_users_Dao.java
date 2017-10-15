package dao.external_users;

import java.util.List;

import model.External_Users;

public interface External_users_Dao {

	public void save(External_Users user);
	public External_Users getbyid(int id);
	public External_Users getbyemail(String email);
	public List<External_Users> getbyname(String name);
	public External_Users getbyssn(String ssn);
	public External_Users getbyphone(String phone);
	public External_Users getbyusername(String user_name);
	public List<External_Users> getbyrole(String priv);

}
