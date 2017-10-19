package security;

import java.util.Map;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import hibernateDao.AccountsDao;
import hibernateDao.ExternalUserDao;
import hibernateDao.InternalUserDao;
import hibernateModel.Accounts;
import hibernateModel.ExternalUser;
import hibernateModel.InternalUser;

public class ModelManager {
	private static final Logger logger = Logger.getLogger(ModelManager.class);

	public static final String EMPTY_ERROR = "Username or password can not be empty";
	public static final String INVALID_CREDENTIAL = "Incorrect Username or password";
	public static final String NO_RESULT = "No results";
	public static final String UNKNOWN_ERROR = "Something went wrong";
	public static final String CONTACT_ADMIN = "Contact admin. Attempts exceeded";
	public static final String DUPLICATE_USER = "User name already exists";
	public static final String NO_SUCH_ACC_OR_USER = "No such account or user exists";

	public static Map<String, String> validateInternalUserById(String name, String pwd) throws DataException {
		try {
			if (name == null || name == "" || pwd == null | pwd == "") {
				throw new DataException(EMPTY_ERROR);
			}
			logger.info("name " + name + " pwd " + pwd);
			InternalUserDao dao = new InternalUserDao();
			InternalUser user = dao.get(name, "user_name");
			if (HashingMasking.verifyPassword(user.getPassword(), pwd)) {
				return ExtraUtil.getMapForIntSessionDetail(user);
			}
		} catch (NullPointerException e1) {
			throw new DataException(INVALID_CREDENTIAL);
		} catch (NoResultException e2) {
			throw new DataException(NO_RESULT);
		} catch (DataException e) {
			throw e;
		} catch (Exception e3) {
			throw new DataException(UNKNOWN_ERROR);
		}
		return null;
	}

	public static Map<String, String> validateExternalUserById(String name, String pwd) throws DataException {
		try {
			if (name == null || name == "" || pwd == null | pwd == "") {
				throw new DataException(EMPTY_ERROR);
			}
			ExternalUserDao dao = new ExternalUserDao();
			ExternalUser user = dao.get(name, "user_name");
			if (user.getAttempt() <= 0) {
				throw new DataException(CONTACT_ADMIN);
			}
			if (HashingMasking.verifyPassword(user.getPassword(), pwd)) {
				if (user.getAttempt() < 3) {
					user.setAttempt(3);
					dao.update(user);
				}
				return ExtraUtil.getMapForExSessionDetail(user);
			} else {
				user.setAttempt(user.getAttempt() - 1);
				dao.update(user);
			}
		} catch (NullPointerException e1) {
			throw new DataException(INVALID_CREDENTIAL);
		} catch (NoResultException e2) {
			throw new DataException(NO_RESULT);
		} catch (DataException e) {
			throw e;
		} catch (Exception e3) {
			throw new DataException(UNKNOWN_ERROR);
		}
		return null;
	}

	public static void setModifiableData(int id, Map<String, String> map) {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(id, "user_id");
		user.setUser_name(map.get("username"));
		user.setAddress(map.get("address"));
		user.setContact_no(map.get("phone"));
		user.setZipcode(map.get("zipcode"));
		user.setEmail(map.get("email"));
		user.setGender(map.get("gender"));
		dao.update(user);
	}

	public static Map<String, String> getModifiableData(int id) {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(id, "user_id");
		return ExtraUtil.getUserInfoAsMap(user);

	}

	public static void createData(Map<String, String> map) throws DataException {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = new ExternalUser(map.get("name"), map.get("ssn"), map.get("email"), map.get("address"),
				map.get("zipcode"), map.get("gender"), map.get("user_name"), map.get("contact_no"), map.get("password"),
				map.get("role"), 3, Long.valueOf(map.get("threshold")));
		try {
			dao.create(user);
		} catch (ConstraintViolationException e) {
			throw new DataException(DUPLICATE_USER);
		}
	}

	public static Map<String, String> getExtUserInfo(String accountId) throws DataException {
		try {
			logger.info("=========" + accountId);
			AccountsDao dao = new AccountsDao();
			Accounts acc = dao.get("123456", "acc_id");
			logger.info("=========" + acc);
			return ExtraUtil.getUserInfoAsMap(acc.getExtUser());
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DataException(NO_SUCH_ACC_OR_USER);
		}
		// return null;
	}

}
