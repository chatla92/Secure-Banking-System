package dao.cards;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class CardRowMapper implements RowMapper<CardResult>{
	  @Override
	  public CardResult mapRow(ResultSet rs, int rowNum) throws SQLException{
	    return new CardResult(rs.getString("card_no"), rs.getString("type"), rs.getLong("balance"), rs.getString("Accounts.type")); 
	}
	}