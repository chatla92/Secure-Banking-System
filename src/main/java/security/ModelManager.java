package security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import hibernateDao.AccountsDao;
import hibernateDao.CreditCardsDao;
import hibernateDao.DebitCardsDao;
import hibernateDao.ExternalAuthorizationDao;
import hibernateDao.ExternalUserDao;
import hibernateDao.InternalAuthorizationDao;
import hibernateDao.InternalUserDao;
import hibernateDao.ModifyUserInfoDao;
import hibernateDao.TransactionDao;
import hibernateModel.Accounts;
import hibernateModel.CreditCards;
import hibernateModel.DebitCards;
import hibernateModel.ExternalAuthorization;
import hibernateModel.ExternalUser;
import hibernateModel.InternalAuthorization;
import hibernateModel.InternalUser;
import hibernateModel.ModifyUserInfo;
import hibernateModel.Transaction;
import web.OTPController;
import web.TransferController;

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

	public static void setModifiableData(String username, Map<String, String> map) throws DataException {
		try {
			ModifyUserInfoDao modifyDao = new ModifyUserInfoDao();
			if (modifyDao.get(username, "user_name") != null) {
				modifyDao.update(ModelUtil.provideUserInfo(username, map));
			} else {
				modifyDao.create(ModelUtil.provideUserInfo(username, map));
			}
		} catch (Exception e) {
			throw new DataException("Fileds cannot be empty or something");
		}
	}

	public static Map<String, String> getModifiableData(int id) {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(id, "user_id");
		return ModelUtil.getUserInfoAsMap(user);

	}

	public static void createNewUser(Map<String, String> map) throws DataException {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = new ExternalUser(map.get("name"), map.get("ssn"), map.get("email"), map.get("address"),
				map.get("zipcode"), map.get("gender"), map.get("username"), map.get("phone"), map.get("password"),
				map.get("role"), 3, Long.valueOf(map.get("threshold")));
		try {
			dao.create(user);
			Accounts acc = createAccount(user);
			createCreditCard(acc);
			// createDebitCard(acc);
		} catch (ConstraintViolationException e) {
			throw new DataException(DUPLICATE_USER);
		}
	}

	public static void createIntUser(Map<String, String> map) throws DataException {
		InternalUserDao dao = new InternalUserDao();
		InternalUser user = new InternalUser(map.get("name"), map.get("ssn"), map.get("email"), map.get("address"),
				map.get("zipcode"), map.get("gender"), map.get("username"), map.get("password"), map.get("role"),
				map.get("phone"), 80000, 2000);
		try {
			dao.create(user);
		} catch (ConstraintViolationException e) {
			throw new DataException(DUPLICATE_USER);
		}
	}

	public static void changePassword(String email, String newPass) {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(email, "email");
		user.setPassword(newPass);
		user.setAttempt(3);
		dao.update(user);
	}

	private static void createDebitCard(Accounts acc) {
		DebitCardsDao dDao = new DebitCardsDao();
		List<DebitCards> list = dDao.findAll();
		long maxAccNo = list.stream().map(n -> Long.valueOf(n.getCard_no())).max(Long::compare).get();
		DebitCards debit = new DebitCards();
		debit.setAccounts(acc);
		debit.setActive(true);
		debit.setCard_no("" + (maxAccNo + 1));
		debit.setCvv(234);
		// dDao.create(debit);
	}

	private static void createCreditCard(Accounts acc) {
		CreditCardsDao dDao = new CreditCardsDao();
		List<CreditCards> list = dDao.findAll();
		long maxAccNo = list.stream().map(n -> Long.valueOf(n.getCard_no())).max(Long::compare).get();
		CreditCards debit = new CreditCards("" + (maxAccNo + 1), acc, 234, 0);
		dDao.create(debit);
	}

	private static Accounts createAccount(ExternalUser user) {
		AccountsDao accountsDao = new AccountsDao();
		List<Accounts> listOfAccounts = accountsDao.findAll();
		long maxAccNo = listOfAccounts.stream().map(n -> Long.valueOf(n.getAcc_id())).max(Long::compare).get();
		Accounts account = new Accounts("" + (maxAccNo + 1), "Saving", 1000, user);
		accountsDao.create(account);
		return accountsDao.get("" + (maxAccNo + 1), "acc_id");
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

	public static List<HashMap<String, String>> getAllTrasactions() {
		TransactionDao transDao = new TransactionDao();
		List<Transaction> trans = transDao.findAll();
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
				isSuccessful = handleCreditCardTrans(extUser, number, amount, false);
			} else {
				isSuccessful = handleAccountTrans(extUser, number, amount, false);
			}
		} catch (Exception e) {
			return false;
		}
		return isSuccessful;
	}

	public static boolean handleIntTransfer(String fromAcc, float amount, String toAcc) {
		boolean isSuccessful = false;
		try {
			AccountsDao accDao = new AccountsDao();
			ExternalUser extUser = accDao.get(toAcc, "acc_id").getExtUser();
			isSuccessful = handleAccountTrans(extUser, fromAcc, amount, true);
		} catch (Exception e) {
			return false;
		}
		return isSuccessful;
	}

	private static boolean handleAccountTrans(ExternalUser extUser, String number, float amount, boolean isInternal) {

		AccountsDao accDao = new AccountsDao();
		Accounts acc = accDao.get(number, "acc_id");
		boolean isSuccessful = false;
		if (amount > acc.getExtUser().getThreshold()) {
			if (isInternal) {
				return false;
			}
			TransferController.OTPrequired = true;

			OTPController.amount = amount;
			OTPController.transType = "DEB";
			OTPController.fromAccNo = number;
			OTPController.toAccNo = extUser.getAccounts().iterator().next().getAcc_id();
			OTPController.initBy = Integer.valueOf(acc.getAcc_id());

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
			TransferController.OTPrequired = true;

			OTPController.amount = amount;
			OTPController.transType = "DEB";
			OTPController.fromAccNo = number;
			OTPController.toAccNo = extUser.getAccounts().iterator().next().getAcc_id();
			OTPController.initBy = Integer.valueOf(cards.getAccounts().getAcc_id());

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

	private static boolean handleCreditCardTrans(ExternalUser extUser, String number, float amount,
			boolean isMerchant) {
		CreditCardsDao dao = new CreditCardsDao();
		CreditCards cards = dao.get(number, "card_no");
		if (cards.getOutstandingamount() + amount <= cards.getMaxlimit()) {
			Transaction ccTrans = createTransEntry(amount, "DEB", number,
					extUser.getAccounts().iterator().next().getAcc_id(), true, false,
					Integer.valueOf(cards.getAccId().getAcc_id()));
			cards.setOutstandingamount(cards.getOutstandingamount() + amount);
			if (isMerchant) {
				createPendingExtAuth(ccTrans, cards.getAccId());
			} else {
				updateReceiverAccount(extUser, amount);
			}
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

	public static void createInternalAuthEntry(Transaction transaction) {
		InternalAuthorizationDao authDao = new InternalAuthorizationDao();
		InternalAuthorization internalAuth = new InternalAuthorization(transaction, "Tier2", false);
		authDao.create(internalAuth);
	}

	public static Transaction createTransEntry(float amount, String type, String fromAcc, String toAcc,
			boolean isCritical, boolean status, int initiatedBy) {
		TransactionDao transDao = new TransactionDao();
		Transaction transaction = new Transaction(amount, type, fromAcc, toAcc, isCritical, status, initiatedBy);
		transDao.create(transaction);
		return transaction;
	}

	public static ArrayList<HashMap<String, String>> getAuthRequests(int id) {
		ExternalUserDao dao = new ExternalUserDao();
		ExternalUser user = dao.get(id, "user_id");
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (Accounts account : user.getAccounts()) {
			ExternalAuthorizationDao extDao = new ExternalAuthorizationDao();
			List<ExternalAuthorization> listOfExtAuth = extDao.getByAccountId(account);
			for (ExternalAuthorization extAuth : listOfExtAuth) {
				list.add(ModelUtil.getTransForExtAuthEntry(extAuth));
			}
		}
		return list;
	}

	public static String handleExtRequestApprove(String transId, boolean isApproved, int userId) {
		boolean isSuccessful = false;
		try {
			String accId = deleteExternalAuthEntry(transId);
			isSuccessful = updateTransEntry(transId, isApproved, userId, accId);
		} catch (Exception e) {
		}
		return isSuccessful ? "Approved successfully" : "Balance is not sufficient";
	}

	private static boolean updateTransEntry(String transId, boolean isApproved, int userId, String accId) {
		TransactionDao transactionDao = new TransactionDao();
		Transaction trans = transactionDao.get(Integer.valueOf(transId), "trans_id");
		AccountsDao accDao = new AccountsDao();
		Accounts account = accDao.get(accId, "acc_id");
		boolean isSuccessful = false;
		if (trans.getAmount() <= account.getBalance()) {
			account.setBalance(account.getBalance() - trans.getAmount());
			accDao.update(account);
			isSuccessful = true;
		}
		trans.setIscritical(false);
		trans.setTransactionStatus(isApproved && isSuccessful);
		trans.setInternalUser(userId);
		transactionDao.update(trans);
		return isSuccessful;
	}

	private static String deleteExternalAuthEntry(String transId) throws Exception {
		String accountId = null;
		ExternalAuthorizationDao extDao = new ExternalAuthorizationDao();
		Transaction transaction = new TransactionDao().get(Integer.valueOf(transId), "trans_id");
		ExternalAuthorization extAuthorization = null;
		for (ExternalAuthorization extAuth : transaction.getExternalAuth()) {
			if (Integer.valueOf(transId) == extAuth.getTransactions().getTrans_id()) {
				extAuthorization = extAuth;
				break;
			}
		}
		if (extAuthorization == null)
			throw new Exception();
		accountId = extAuthorization.getAccount().getAcc_id();
		extDao.delete(extAuthorization);
		return accountId;
	}

	public static boolean handleExtRequest(String email, float amount, String accType, String fromAcc) {
		ExternalUserDao extDao = new ExternalUserDao();
		ExternalUser extUser = extDao.get(email, "email");
		String toAccountId = extUser.getAccounts().iterator().next().getAcc_id();
		Transaction transaction = createTransEntry(amount, "DEB", fromAcc, toAccountId, false, true,
				Integer.valueOf(fromAcc));
		return createPendingExtAuth(transaction, extUser.getAccounts().iterator().next());
	}

	private static boolean createPendingExtAuth(Transaction transaction, Accounts accounts) {
		boolean isSuccessful = false;
		try {
			ExternalAuthorizationDao extDao = new ExternalAuthorizationDao();
			ExternalAuthorization extAuth = new ExternalAuthorization(transaction, accounts, false);
			extDao.create(extAuth);
			isSuccessful = true;
		} catch (Exception e) {

		}
		return isSuccessful;
	}

	public static ArrayList<HashMap<String, String>> getModifyRequests() {
		ModifyUserInfoDao modifyDao = new ModifyUserInfoDao();
		List<ModifyUserInfo> listOfModifyInfo = modifyDao.findAll();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (ModifyUserInfo modify : listOfModifyInfo) {
			list.add(ModelUtil.getMapForPendingModify(modify));
		}
		return list;
	}

	public static String handleIntModifyApprove(String idNo, boolean equals) {
		boolean isSuccessful = false;
		try {
			ModifyUserInfoDao modifyDao = new ModifyUserInfoDao();
			ModifyUserInfo userInfo = modifyDao.get(Integer.parseInt(idNo), "id");
			isSuccessful = updateExtUserEntry(userInfo);
			modifyDao.delete(userInfo);

		} catch (Exception e) {
		}
		return isSuccessful ? "Modified successfully" : "Modification Unseccessful";
	}

	private static boolean updateExtUserEntry(ModifyUserInfo userInfo) {
		boolean isSuccessful = false;
		try {
			ExternalUserDao extDao = new ExternalUserDao();
			ExternalUser extUser = extDao.get(userInfo.getUser_name(), "user_name");
			extUser.setEmail(userInfo.getEmail());
			extUser.setAddress(userInfo.getAddress());
			extUser.setZipcode(userInfo.getZipcode());
			extUser.setContact_no(userInfo.getContact_no());
			extUser.setGender(userInfo.getGender());
			extDao.update(extUser);
			isSuccessful = true;
		} catch (Exception e) {

		}
		return isSuccessful;
	}

	public static List<HashMap<String, String>> getCriticalTxs() {
		TransactionDao transDao = new TransactionDao();
		return ModelUtil.convertToListOfTransaction(transDao.getCriticalTrans());
	}

	public static String handleCriticalTxs(String transId, boolean isApproved, int approvedBy) {
		boolean isSuccessful = false;
		try {
			deleteInternalAuthEntry(transId);
			TransactionDao transactionDao = new TransactionDao();
			Transaction trans = transactionDao.get(Integer.valueOf(transId), "trans_id");
			String accId = trans.getTo_acc();
			isSuccessful = updateTransEntry(transId, isApproved, approvedBy, accId);
			// email notification needed
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccessful ? "Modified successfully" : "Balance is not sufficient";
	}

	private static void deleteInternalAuthEntry(String transId) throws Exception {
		InternalAuthorizationDao intDao = new InternalAuthorizationDao();
		Transaction transaction = new TransactionDao().get(Integer.valueOf(transId), "trans_id");
		InternalAuthorization intAuthorization = null;
		for (InternalAuthorization intAuth : transaction.getInternalAuth()) {
			if (Integer.valueOf(transId) == intAuth.getTransaction().getTrans_id()) {
				intAuthorization = intAuth;
				break;
			}
		}
		if (intAuthorization == null)
			throw new Exception();
		intDao.delete(intAuthorization);
	}

	public static boolean handleExtRequest(String ccNo, String cvv, float amount, String string, String string2) {
		AccountsDao accDao = new AccountsDao();
		Accounts acc = accDao.get(string2, "acc_id");
		CreditCardsDao dao = new CreditCardsDao();
		CreditCards cards = dao.get(ccNo, "card_no");
		if (cvv.equals("" + cards.getCvv()))
			return handleCreditCardTrans(acc.getExtUser(), ccNo, amount, true);
		return false;
	}

	public static Map<String, String> getIntUserInfo(String empId) throws DataException {
		try {
			if (empId != null && !empId.equals("")) {
				InternalUserDao dao = new InternalUserDao();
				InternalUser user = dao.get(Integer.valueOf(empId), "emp_id");
				return ModelUtil.getEmpInfoAsMap(user);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new DataException(NO_SUCH_ACC_OR_USER);
		}
		return null;
	}

	public static void modifyEmpData(String username, Map<String, String> map) throws DataException {
		try {
			InternalUserDao internalUserDao = new InternalUserDao();
			if (internalUserDao.get(username, "user_name") != null) {
				internalUserDao.update(ModelUtil.updateEmpInfo(internalUserDao.get(username, "user_name"), map));
			}
		} catch (Exception e) {
			throw new DataException("Fileds cannot be empty or something");
		}
	}

	public static boolean deleteExtUser(String userId) {
		try {
			if (userId != null && !userId.equals("")) {
				ExternalUserDao extDao = new ExternalUserDao();
				ExternalUser extUser = extDao.get(Integer.valueOf(userId), "user_id");
				if (extUser != null) {
					extDao.delete(extUser);
					return true;
				}
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;

	}

	public static boolean deleteIntUser(String userId) {
		try {
			if (userId != null && !userId.equals("")) {
				InternalUserDao intDao = new InternalUserDao();
				InternalUser intUser = intDao.get(Integer.valueOf(userId), "emp_id");
				if (intUser != null) {
					intDao.delete(intUser);
					return true;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public static String getPII(int id, boolean customer) {
		String unmaskedSSN = "";
		if (customer) {
			ExternalUserDao externalUserDao = new ExternalUserDao();
			ExternalUser externalUser = externalUserDao.get(id, "user_id");

			if (externalUser != null) {
				String maskedSSN = externalUser.getSsn();
				unmaskedSSN = HashingMasking.unMask(maskedSSN);
				unmaskedSSN+=","+externalUser.getName();
			}

		} else if(!customer) {
			InternalUserDao internalUserDao = new InternalUserDao();
			InternalUser internalUser = internalUserDao.get(id, "emp_id");
			if (internalUser != null) {
				String maskedSSN = internalUser.getSsn();
				unmaskedSSN = HashingMasking.unMask(maskedSSN);
				unmaskedSSN+=","+internalUser.getName();
			}
		}
		return unmaskedSSN;
	}

	public static ArrayList<HashMap<String, String>> getInternalUserList() {
		InternalUserDao internalUserDao = new InternalUserDao();
		List<InternalUser> internalUser = internalUserDao.findAll();
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for(InternalUser user:internalUser) {
			list.add((HashMap<String, String>) ModelUtil.getEmpInfoAsMapForAdmin(user));
		}
		return list;
	}

}
