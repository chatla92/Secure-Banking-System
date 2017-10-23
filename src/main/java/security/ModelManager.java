package security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;

import hibernateDao.*;
import hibernateModel.*;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

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
				return ModelUtil.getMapForIntSessionDetail(user);
			}
		} catch (NullPointerException e1) {
			throw new DataException(INVALID_CREDENTIAL);
		} catch (NoResultException e2) {
			throw new DataException(NO_RESULT);
		} catch (DataException e) {
			throw e;
		} catch (Exception e3) {
			e3.printStackTrace();
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
				return ModelUtil.getMapForExSessionDetail(user);
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
			e3.printStackTrace();
			throw new DataException(UNKNOWN_ERROR);
		}
		return null;
	}

	public static void setModifiableData(String username, Map<String, String> map) {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(username, "user_name");
		dao.update(ModelUtil.updateUserInfo(user, map));
	}

	public static Map<String, String> getModifiableData(int id) {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(id, "user_id");
		return ModelUtil.getUserInfoAsMap(user);

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
			if (accountId != null && !accountId.equals("")) {
				AccountsDao dao = new AccountsDao();
				Accounts acc = dao.get(accountId, "acc_id");
				logger.info("=========" + acc);
				return ModelUtil.getUserInfoAsMap(acc.getExtUser());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DataException(NO_SUCH_ACC_OR_USER);
		}
		return null;
	}

	public static List<HashMap<String, String>> getCompleteTrasaction(String acc_no) {
		TransactionDao transDao = new TransactionDao();
		List<Transaction> trans = transDao.getByType(acc_no);
		return ModelUtil.convertToListOfTransaction(trans);
	}

	public static List<HashMap<String, String>> getCompleteRequest(String acc_no){
		ExternalAuthorizationDao exDAO = new ExternalAuthorizationDao();
		List<Transaction> trans = exDAO.getByType(acc_no);
		return ModelUtil.convertToListOfTransaction(trans);
	}

	public static ArrayList<LinkedHashMap<String, String>> getAccountList(int userId) {
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(userId, "user_id");
		ModelUtil.getAccountsAsList(user, list);
		System.out.println(list);
		return list;
	}

	public static boolean handleExtTransfer(String email, float amount, String type, String number) {
		boolean isSuccessful = false;
		try {
			ExternalUserDao extDao = new ExternalUserDao();
			ExternalUser extUser = extDao.get(email, "email");
			if (type.equals("Debit")) {
				isSuccessful = handleDebitCardTrans(extUser, number, amount);
			} else if (type.equals("Credit")) {
				isSuccessful = handleCreditCardTrans(extUser, number, amount);
			} else {
				isSuccessful = handleAccountTrans(extUser, number, amount);
			}
		} catch (Exception e) {
			return false;
		}
		return isSuccessful;
	}

	public static boolean sendExtReq(String email, float amount, String type, String number){

		AccountsDao accountsDao = new AccountsDao();
		Accounts accounts = accountsDao.get(number, "acc_id");
		ExternalUserDao extDao = new ExternalUserDao();
		ExternalUser extUser = extDao.get(email, "email");
		boolean isSuccessful = false;
		try {
			if (type.equals("Debit")) {
				isSuccessful = handleDebitCardReq(extUser, number, amount);
			} else if (type.equals("Credit")) {
				int k = 0;
			} else {
				isSuccessful = handleAccountReq(extUser, number, amount);
			}
		}
		catch (Exception e) {
			return false;
		}
		return isSuccessful;
	}

	private static boolean handleAccountReq(ExternalUser extUser, String number, float amount) {

		AccountsDao accDao = new AccountsDao();
		Accounts acc = accDao.get(number, "acc_id");
		boolean isSuccessful = false;
		if (amount > acc.getExtUser().getThreshold()) {
			Transaction transaction = createTransEntry(amount, "DEB", extUser.getAccounts().iterator().next().getAcc_id(),
					number, true, false, Integer.valueOf(acc.getAcc_id()));
			Accounts accq = accDao.get(extUser.getAccounts().iterator().next().getAcc_id(), "acc_id");
			createExtAuthEntry(transaction, accq);
			isSuccessful = true;
		}
		else{
			Transaction transaction = createTransEntry(amount, "DEB", extUser.getAccounts().iterator().next().getAcc_id(),
					number, false, false, Integer.valueOf(acc.getAcc_id()));
			Accounts accq = accDao.get(extUser.getAccounts().iterator().next().getAcc_id(), "acc_id");
			createExtAuthEntry(transaction, accq);
			isSuccessful = true;
		}
		return isSuccessful;
	}


	private static boolean handleDebitCardReq(ExternalUser extUser, String number, float amount) {
		DebitCardsDao dao = new DebitCardsDao();
		DebitCards cards = dao.get(number, "card_no");
		boolean isSuccessful = false;
		if (amount > cards.getAccounts().getExtUser().getThreshold()) {
			Transaction transaction = createTransEntry(amount, "DEB", extUser.getAccounts().iterator().next().getAcc_id(),
					number, true, false,
					Integer.valueOf(cards.getAccounts().getAcc_id()));
			AccountsDao accDao = new AccountsDao();
			Accounts accq = accDao.get(extUser.getAccounts().iterator().next().getAcc_id(), "acc_id");
			createExtAuthEntry(transaction, accq);
			isSuccessful = true;
		}
		else{
				Transaction transaction = createTransEntry(amount, "DEB", extUser.getAccounts().iterator().next().getAcc_id(),
						number, false, false,
						Integer.valueOf(cards.getAccounts().getAcc_id()));
				AccountsDao accDao = new AccountsDao();
				Accounts accq = accDao.get(extUser.getAccounts().iterator().next().getAcc_id(), "acc_id");
				createExtAuthEntry(transaction, accq);
				isSuccessful = true;
		}
		return isSuccessful;
	}



	private static boolean handleAccountTrans(ExternalUser extUser, String number, float amount) {

		AccountsDao accDao = new AccountsDao();
		Accounts acc = accDao.get(number, "acc_id");
		boolean isSuccessful = false;
		if (amount > acc.getExtUser().getThreshold()) {
			Transaction transaction = createTransEntry(amount, "DEB", number,
					extUser.getAccounts().iterator().next().getAcc_id(), true, false, Integer.valueOf(acc.getAcc_id()));
			createInternalAuthEntry(transaction);
			isSuccessful = true;
		} else {
			float temp = acc.getBalance() - amount;
			if (temp > 0) {
				createTransEntry(amount, "DEB", number, extUser.getAccounts().iterator().next().getAcc_id(), false,
						true, Integer.valueOf(acc.getAcc_id()));
				updateReceiverAccount(extUser, amount);
				updateSenderAccount(acc, accDao, temp);
				isSuccessful = true;
			} else {
				isSuccessful = false;
			}
		}
		return isSuccessful;
	}

	private static boolean handleDebitCardTrans(ExternalUser extUser, String number, float amount) {
		DebitCardsDao dao = new DebitCardsDao();
		DebitCards cards = dao.get(number, "card_no");
		boolean isSuccessful = false;
		if (amount > cards.getAccounts().getExtUser().getThreshold()) {
			Transaction transaction = createTransEntry(amount, "DEB", number,
					extUser.getAccounts().iterator().next().getAcc_id(), true, false,
					Integer.valueOf(cards.getAccounts().getAcc_id()));
			createInternalAuthEntry(transaction);
			isSuccessful = true;
		} else {
			AccountsDao accDao = new AccountsDao();
			Accounts acc = accDao.get(cards.getAccounts().getAcc_id(), "acc_id");
			float temp = acc.getBalance() - amount;
			if (temp > 0) {
				createTransEntry(amount, "DEB", number, extUser.getAccounts().iterator().next().getAcc_id(), false,
						true, Integer.valueOf(cards.getAccounts().getAcc_id()));
				updateReceiverAccount(extUser, amount);
				updateSenderAccount(acc, accDao, temp);
				isSuccessful = true;
			} else {
				isSuccessful = false;
			}
		}
		return isSuccessful;
	}

	private static boolean handleCreditCardTrans(ExternalUser extUser, String number, float amount) {
		CreditCardsDao dao = new CreditCardsDao();
		CreditCards cards = dao.get(number, "card_no");
		if (cards.getOutstandingamount() + amount <= cards.getMaxlimit()) {
			createTransEntry(amount, "DEB", number, extUser.getAccounts().iterator().next().getAcc_id(), true, false,
					Integer.valueOf(cards.getAccId().getAcc_id()));
			cards.setOutstandingamount(cards.getOutstandingamount() + amount);
			updateReceiverAccount(extUser, amount);
			dao.update(cards);
			return true;
		}
		return false;
	}

	private static void updateSenderAccount(Accounts acc, AccountsDao accDao, float amount) {
		acc.setBalance(amount);
		accDao.update(acc);
	}

	private static void updateReceiverAccount(ExternalUser extUser, float amount) {
		AccountsDao accDao = new AccountsDao();
		Accounts rec_acc = extUser.getAccounts().iterator().next();
		rec_acc.setBalance(rec_acc.getBalance() + amount);
		accDao.update(rec_acc);
	}

	private static void createInternalAuthEntry(Transaction transaction) {
		InternalAuthorizationDao authDao = new InternalAuthorizationDao();
		InternalAuthorization internalAuth = new InternalAuthorization(transaction, "Tier2", false);
		authDao.create(internalAuth);
	}

	private static Transaction createTransEntry(float amount, String type, String fromAcc, String toAcc,
			boolean isCritical, boolean status, int initiatedBy) {
		TransactionDao transDao = new TransactionDao();
		Transaction transaction = new Transaction(amount, type, fromAcc, toAcc, isCritical, status, initiatedBy);
		transDao.create(transaction);
		return transaction;
	}

	private static void createExtAuthEntry(Transaction transaction, Accounts account){
		ExternalAuthorizationDao authDoa = new ExternalAuthorizationDao();
		ExternalAuthorization externalAuthorization = new ExternalAuthorization(transaction , account, false);
		authDoa.create(externalAuthorization);
	}

}
