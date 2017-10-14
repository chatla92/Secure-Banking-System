package dao.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class TransactionDao {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	@Autowired
	public void setdataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean saveOrUpdate(Transactions transaction) {
		String query = "INSERT INTO transactions(trans_id, amount, type, from_acc, to_acc, isActive, emp_id, init_id) VALUES (?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, transaction.getTrans_id());
			ps.setLong(2, transaction.getAmount());
			ps.setString(3, transaction.getType());
			ps.setString(4, transaction.getFrom_acc());
			ps.setString(5, transaction.getTo_acc());
			ps.setBoolean(6, transaction.isActive());
			ps.setInt(7, transaction.getEmp_id());
			ps.setInt(8, transaction.getInit_id());
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

	public Transactions getByEmpId(int id) {
		String query = "Select * from transactions where emp_id=" + id;
		return executeGetQuery(query);

	}

	public Transactions getByTransactionId(int id) {
		String query = "Select * from transactions where trans_id=" + id;
		return executeGetQuery(query);
	}

	public Transactions getByAuthState(boolean state) {
		String query = "Select * from transactions where isActive=" + state;
		return executeGetQuery(query);
	}

	public Transactions getByInitId(int id) {
		String query = "Select * from transactions where init_id=" + id;
		return executeGetQuery(query);
	}
	
	public Transactions getByType(String type) {
		String query = "Select * from transactions where type=\"" + type+ "\"";
		return executeGetQuery(query);
	}
	
	public Transactions getByAmount(Long amount) {
		String query = "Select * from transactions where amount="+ amount;
		return executeGetQuery(query);
	}
	
	public Transactions getByFromAcc(String accnt) {
		String query = "Select * from transactions where from_acc="+ accnt;
		return executeGetQuery(query);
	}
	
	public Transactions getByToAcc(String accnt) {
		String query = "Select * from transactions where to_acc="+ accnt;
		return executeGetQuery(query);
	}
	
	private Transactions executeGetQuery(String query) {
		List<Transactions> users;
		users = jdbcTemplate.query(query,
				new BeanPropertyRowMapper<Transactions>(
						Transactions.class));
		if (users.size() > 0)
			return users.get(0);
		else
			return null;
	}

}
