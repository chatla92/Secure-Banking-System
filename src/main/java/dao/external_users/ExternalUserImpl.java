package dao.external_users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import model.External_Users;

public class ExternalUserImpl implements External_users_Dao{
	 private JdbcTemplate jdbcTemplate;
		private DataSource dataSource;

		@Autowired
		public void setdataSource(DataSource dataSource) {
			this.dataSource = dataSource;
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
		@Override
		public void save(External_Users user)
		{
			String sql="INSERT INTO external_users (user_id,name,ssn,email,address,zipcode,gender,contact_no,user_name,password,role,threshold) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			Connection con = null;
	        PreparedStatement ps = null;
	        try{
	            con = dataSource.getConnection();
	            ps = con.prepareStatement(sql);
	            ps.setInt(1,user.getUser_id());
	            ps.setString(2,user.getName());
	            ps.setString(3, user.getSsn());
	            ps.setString(4, user.getEmail());
	            ps.setString(5, user.getAddress());
	            ps.setString(6, user.getZipcode());
	            ps.setString(7, user.getGender());
	            ps.setString(8, user.getContact_no());
	            ps.setString(9, user.getUser_name());
	            ps.setLong(12, user.getThreshold());
	            ps.setString(10, user.getPassword());
	            ps.setString(11, user.getRole());
	            int out = ps.executeUpdate();
	        }
	        catch(SQLException e)
	        {
	        		e.printStackTrace();
	        }
	        finally{
	            try {
	                ps.close();
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		}
		public External_Users getbyid(int id)
		{
			String query="Select * from external_users where user_id="+id;
			List<External_Users> users;
		    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<External_Users>(External_Users.class));
			if(users.size()>0)
				return users.get(0);
			else
				return null;
			
		}
		public External_Users getbyemail(String email)
		{
			String query="Select * from external_users where email='"+email+"'";
			List<External_Users> users;
		    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<External_Users>(External_Users.class));
			if(users.size()>0)
				return users.get(0);
			else
				return null;
		}
		public List<External_Users> getbyname(String name)
		{
			String query="Select * from external_users where name='"+name+"'";
			List<External_Users> users;
		    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<External_Users>(External_Users.class));
			return users;
			
		}
		public External_Users getbyssn(String ssn)
		{
			String query="Select * from external_users where ssn="+ssn;
			List<External_Users> users;
		    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<External_Users>(External_Users.class));
			if(users.size()>0)
				return users.get(0);
			else
				return null;
			
		}
		public External_Users getbyphone(String phone)
		{
			String query="Select * from external_users where contact_no="+phone;
			List<External_Users> users;
		    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<External_Users>(External_Users.class));
			if(users.size()>0)
				return users.get(0);
			else
				return null;
			
		}
		public External_Users getbyusername(String user_name)
		{
			String query="Select * from external_users where user_name='"+user_name+"'";
			List<External_Users> users;
		    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<External_Users>(External_Users.class));
			if(users.size()>0)
				return users.get(0);
			else
				return null;
			
		}
		public List<External_Users> getbyrole(String priv)
		{
			String query="Select * from internal_users where role='"+priv+"'";
			List<External_Users> users;
		    users=jdbcTemplate.query(query,new BeanPropertyRowMapper<External_Users>(External_Users.class));
			return users;
		}

}
