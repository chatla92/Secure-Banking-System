package model;

public class Authorization_Internal {
	private int trans_id, emp_id;
	private boolean auth_state;
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public boolean isAuth_state() {
		return auth_state;
	}
	public void setAuth_state(boolean auth_state) {
		this.auth_state = auth_state;
	}
}
