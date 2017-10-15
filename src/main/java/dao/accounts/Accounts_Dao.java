package dao.accounts;

import java.util.List;

import model.Accounts;

public interface Accounts_Dao {
	public void save(Accounts acc);
	public Accounts getbyaccid(String id);
	public List<Accounts> getbytype(String type);
	public List<Accounts> getbyuserid(int userid);
}
