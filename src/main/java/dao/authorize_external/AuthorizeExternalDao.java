package dao.authorize_external;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Authorization_External;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorizeExternalDao {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	@Autowired
	public void setdataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean saveOrUpdate(Authorization_External authorizeExternal) {
		String query = "INSERT INTO Pending_Auth_Ext(trans_id, acc_id, isAuth) VALUES (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, authorizeExternal.getTrans_id());
			ps.setString(2, authorizeExternal.getAcc_id());
			ps.setBoolean(3, authorizeExternal.isAuth());
			int out = ps.executeUpdate();
			if (out == 0) {
				return false;
			}
			if (dataSource == null)
				System.out.println("Data Source is null.");
		} catch (SQLException e) {
			e.printStackTrace();
			// e1.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	public Authorization_External getByAccId(String id) {
		String query = "Select * from Pending_Auth_Ext where acc_id=\"" + id
				+ "\"";
		return executeGetQuery(query);

	}

	public Authorization_External getByTransactionId(int id) {
		String query = "Select * from Pending_Auth_Ext where trans_id=" + id;
		return executeGetQuery(query);
	}

	public Authorization_External getByAuthState(boolean state) {
		String query = "Select * from Pending_Auth_Ext where isAuth=" + state;
		return executeGetQuery(query);
	}

	private Authorization_External executeGetQuery(String query) {
		List<Authorization_External> users;
		users = jdbcTemplate.query(query,
				new BeanPropertyRowMapper<Authorization_External>(
						Authorization_External.class));
		if (users.size() > 0)
			return users.get(0);
		else
			return null;
	}
}
