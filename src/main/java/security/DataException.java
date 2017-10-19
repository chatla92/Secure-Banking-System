package security;

public class DataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public DataException(String message) {
		// TODO Auto-generated constructor stub
		this.message = message;
	}

	public String getMessageDetail() {
		return message;
	}
}
