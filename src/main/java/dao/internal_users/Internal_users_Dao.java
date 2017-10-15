package dao.internal_users;

import java.util.List;

import model.Internal_Users;

public interface Internal_users_Dao {

	public boolean saveOrUpdate(Internal_Users user);
	public Internal_Users getbyid(int id);
	public Internal_Users getbyemail(String email);
	public List<Internal_Users> getbyname(String name);
	public Internal_Users getbyssn(String ssn);
	public Internal_Users getbyphone(String phone);
	public Internal_Users getbyusername(String user_name);
	public List<Internal_Users> getbyrole(String priv);
}
