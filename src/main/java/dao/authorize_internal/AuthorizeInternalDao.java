package dao.authorize_internal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Authorization_Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorizeInternalDao {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	@Autowired
	public void setdataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean saveOrUpdate(Authorization_Internal authorizeInternal) {
		String query = "INSERT INTO Pending_Auth(trans_id, emp_id, auth_state) VALUES (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, authorizeInternal.getTrans_id());
			ps.setInt(2, authorizeInternal.getEmp_id());
			ps.setBoolean(3, authorizeInternal.isAuth_state());
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

	public Authorization_Internal getByEmpId(int id) {
		String query = "Select * from Pending_Auth where emp_id=" + id;
		return executeGetQuery(query);

	}

	public Authorization_Internal getByTransactionId(int id) {
		String query = "Select * from Pending_Auth where trans_id=" + id;
		return executeGetQuery(query);
	}

	public Authorization_Internal getByAuthState(boolean state) {
		String query = "Select * from Pending_Auth where auth_state=" + state;
		return executeGetQuery(query);
	}

	private Authorization_Internal executeGetQuery(String query) {
		List<Authorization_Internal> users;
		users = jdbcTemplate.query(query,
				new BeanPropertyRowMapper<Authorization_Internal>(
						Authorization_Internal.class));
		if (users.size() > 0)
			return users.get(0);
		else
			return null;
	}
}
