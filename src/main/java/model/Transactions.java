package model;

public class Transactions {
	private int trans_id;
	private long amount;
	private String type;
	private String from_acc, to_acc;
	private boolean isActive;
	private int emp_id, init_id;
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFrom_acc() {
		return from_acc;
	}
	public void setFrom_acc(String from_acc) {
		this.from_acc = from_acc;
	}
	public String getTo_acc() {
		return to_acc;
	}
	public void setTo_acc(String to_acc) {
		this.to_acc = to_acc;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public int getInit_id() {
		return init_id;
	}
	public void setInit_id(int init_id) {
		this.init_id = init_id;
	}
}
