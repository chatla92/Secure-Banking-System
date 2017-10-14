package model;

public class Authorization_External {
	private int trans_id;
	private String acc_id;
	private boolean isAuth;
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	public String getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(String acc_id) {
		this.acc_id = acc_id;
	}
	public boolean isAuth() {
		return isAuth;
	}
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
}
