package dao.accounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Accounts;
import model.External_Users;

public class AccountsImpl implements Accounts_Dao{
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	@Autowired
	public void setdataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public void save(Accounts acc)
	{
		String sql="INSERT INTO Accounts(acc_id, type, balance, user_id) VALUES(?,?,?,?)";
		Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,acc.getAcc_id());
            ps.setString(2,acc.getType());
            ps.setLong(3,acc.getBalance());
            ps.setInt(4,acc.getUser_id());
            int out=ps.executeUpdate();
        }
        catch(SQLException e)
        {
        		e.printStackTrace();
        }
	}
	public Accounts getbyaccid(String id)
	{
		String query="Select * from accounts where acc_id="+id;
		List<Accounts> accounts;
	    accounts=jdbcTemplate.query(query,new BeanPropertyRowMapper<Accounts>(Accounts.class));
		if(accounts.size()>0)
			return accounts.get(0);
		else
			return null;
		
	}
	public List<Accounts> getbytype(String type)
	{
		String query="Select * from accounts where type='"+type+"'";
		List<Accounts> accounts;
	    accounts=jdbcTemplate.query(query,new BeanPropertyRowMapper<Accounts>(Accounts.class));
		return accounts;
		
	}
	public List<Accounts> getbyuserid(int userid)
	{
		String query="Select * from accounts where user_id="+userid;
		List<Accounts> accounts;
	    accounts=jdbcTemplate.query(query,new BeanPropertyRowMapper<Accounts>(Accounts.class));
		return accounts;
		
	}
}
