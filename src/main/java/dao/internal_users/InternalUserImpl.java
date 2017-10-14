package dao.internal_users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.metadata.GenericTableMetaDataProvider;

import model.Internal_Users;

public class InternalUserImpl implements Internal_users_Dao {
	  private JdbcTemplate jdbcTemplate;
		private DataSource dataSource;

		@Autowired
		public void setdataSource(DataSource dataSource) {
			this.dataSource = dataSource;
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}

	@Override
	public boolean saveOrUpdate(Internal_Users user) {
		 String query = "INSERT INTO internal_users (emp_id,name,ssn,email,address,zipcode,gender,contact_no,user_name,password,role,salary) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	        Connection con = null;
	        PreparedStatement ps = null;
	        try{
	            con = dataSource.getConnection();
	            ps = con.prepareStatement(query);
	            ps.setInt(1,user.getEmp_id());
	            ps.setString(2,user.getName());
	            ps.setString(3, user.getSsn());
	            ps.setString(4, user.getEmail());
	            ps.setString(5, user.getAddress());
	            ps.setString(6, user.getZipcode());
	            ps.setString(7, user.getGender());
	            ps.setString(8, user.getContact_no());
	            ps.setString(9, user.getUsername());
	            ps.setLong(12, user.getSalary());
	            ps.setString(10, user.getPassword());
	            ps.setString(11, user.getPriv());
	            int out = ps.executeUpdate();
	            if(out !=0){
	                
	            }else return false;
	            if(dataSource==null)
	            		System.out.println("Data Source is null.");
	        }catch(SQLException e){
	            e.printStackTrace();
	            //e1.printStackTrace();
	        }finally{
	            try {
	                ps.close();
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return false;
		
	}
	public Internal_Users getbyid(int id)
	{
		String query="Select * from internal_users where emp_id="+id;
		List<Internal_Users> users;
	    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<Internal_Users>(Internal_Users.class));
		if(users.size()>0)
			return users.get(0);
		else
			return null;
		
	}
	public Internal_Users getbyemail(String email)
	{
		String query="Select * from internal_users where email='"+email+"'";
		List<Internal_Users> users;
	    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<Internal_Users>(Internal_Users.class));
		if(users.size()>0)
			return users.get(0);
		else
			return null;
	}
	public List<Internal_Users> getbyname(String name)
	{
		String query="Select * from internal_users where name='"+name+"'";
		List<Internal_Users> users;
	    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<Internal_Users>(Internal_Users.class));
		return users;
		
	}
	public Internal_Users getbyssn(String ssn)
	{
		String query="Select * from internal_users where ssn="+ssn;
		List<Internal_Users> users;
	    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<Internal_Users>(Internal_Users.class));
		if(users.size()>0)
			return users.get(0);
		else
			return null;
		
	}
	public Internal_Users getbyphone(String phone)
	{
		String query="Select * from internal_users where contact_no="+phone;
		List<Internal_Users> users;
	    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<Internal_Users>(Internal_Users.class));
		if(users.size()>0)
			return users.get(0);
		else
			return null;
		
	}
	public Internal_Users getbyusername(String user_name)
	{
		String query="Select * from internal_users where user_name='"+user_name+"'";
		List<Internal_Users> users;
	    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<Internal_Users>(Internal_Users.class));
		if(users.size()>0)
			return users.get(0);
		else
			return null;
		
	}
	public List<Internal_Users> getbyrole(String priv)
	{
		String query="Select * from internal_users where role='"+priv+"'";
		List<Internal_Users> users;
	    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<Internal_Users>(Internal_Users.class));
		return users;
	}
}
