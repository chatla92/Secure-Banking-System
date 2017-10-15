package dao.cards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import model.Accounts;
import model.Cards;

public class CardsImpl implements Cards_Dao{
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	@Autowired
	public void setdataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public void save(Cards card)
	{
		String sql="INSERT INTO Cards(card_no, type, acc_id, cvv, isActive) VALUES(?,?,?,?,?)";
		Connection con=null;
		PreparedStatement ps=null;
		try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, card.getCard_no());
            ps.setString(2, card.getType());
            ps.setString(3, card.getAcc_id());
            ps.setInt(4, card.getCvv());
            ps.setBoolean(5, card.isActive());
            int out=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public Cards getbyaccid(String id)
	{
		String query="Select * from cards where acc_id="+id;
		List<Cards> cards;
	    cards=jdbcTemplate.query(query,new BeanPropertyRowMapper<Cards>(Cards.class));
		if(cards.size()>0)
			return cards.get(0);
		else
			return null;
		
	}
	public Cards getbycardno(String id)
	{
		String query="Select * from cards where card_no="+id;
		List<Cards> cards;
	    cards=jdbcTemplate.query(query,new BeanPropertyRowMapper<Cards>(Cards.class));
		if(cards.size()>0)
			return cards.get(0);
		else
			return null;
		
	}
	public Cards getbytype(String id)
	{
		String query="Select * from cards where type='"+id+"'";
		List<Cards> cards;
	    cards=jdbcTemplate.query(query,new BeanPropertyRowMapper<Cards>(Cards.class));
		if(cards.size()>0)
			return cards.get(0);
		else
			return null;
		
	}
	public CardResult getcardbyacc_id(String id)
	{
		String query="Select card_no, Cards.type, Accounts.balance, Accounts.type from Cards inner join Accounts on Cards.acc_id=Accounts.acc_id where cards.acc_id="+id;
		List<CardResult> card;
		card=jdbcTemplate.query(query, new CardRowMapper());
		return card.get(0);
	}
}
