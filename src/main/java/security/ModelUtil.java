package security;

import java.util.ArrayList;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import hibernateModel.Accounts;
import hibernateModel.CreditCards;
import hibernateModel.DebitCards;
import hibernateModel.ExternalAuthorization;
import hibernateModel.ExternalUser;
import hibernateModel.InternalUser;
import hibernateModel.ModifyUserInfo;
import hibernateModel.Transaction;

public class ModelUtil {

	public static Map<String, String> getUserInfoAsMap(ExternalUser user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", escapeHtml(user.getUser_name()));
		map.put("address", escapeHtml(user.getAddress()));
		map.put("phone", escapeHtml(user.getContact_no()));
		map.put("zipcode", escapeHtml(user.getZipcode()));
		map.put("email", escapeHtml(user.getEmail()));
		map.put("gender", escapeHtml(user.getGender()));
		return map;
	}

	public static Map<String, String> getEmpInfoAsMap(InternalUser user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", escapeHtml(user.getUser_name()));
		map.put("address", escapeHtml(user.getAddress()));
		map.put("phone", escapeHtml(user.getContact_no()));
		map.put("zipcode", escapeHtml(user.getZipcode()));
		map.put("email", escapeHtml(user.getEmail()));
		map.put("gender", escapeHtml(user.getGender()));
		return map;
	}

	public static Map<String, String> getEmpInfoAsMapForAdmin (InternalUser user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("empId", escapeHtml(""+user.getEmp_id()));
		map.put("username", escapeHtml(user.getUser_name()));
		map.put("phone", escapeHtml(user.getContact_no()));
		map.put("email", escapeHtml(user.getEmail()));
		return map;
	}
	
	public static Map<String, String> getMapForExSessionDetail(ExternalUser user) {
		Map<String, String> result = null;
		result = new HashMap<String, String>();
		result.put("role", user.getRole());
		result.put("id", "" + user.getUser_id());
		result.put("name", user.getName());
		return result;
	}

	public static Map<String, String> getMapForIntSessionDetail(InternalUser user) {
		Map<String, String> result = null;
		result = new HashMap<String, String>();
		result.put("role", user.getRole());
		result.put("id", "" + user.getEmp_id());
		result.put("name", user.getName());
		return result;
	}

	public static ModifyUserInfo provideUserInfo(String user, Map<String, String> map) {
		ModifyUserInfo userInfo = new ModifyUserInfo(map.get("email"), map.get("address"), map.get("zipcode"),
				map.get("gender"), map.get("phone"), user);
		return userInfo;
	}

	public static void getAccountsAsList(ExternalUser user, ArrayList<LinkedHashMap<String, String>> list) {
		for (Accounts acc : user.getAccounts()) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put(acc.getAcc_id(), acc.getType());
			list.add(map);
			for (CreditCards credCard : acc.getCreditCards()) {
				LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
				map1.put(credCard.getCard_no(), "Credit");
				list.add(map1);
			}
			for (DebitCards debitCard : acc.getDebitCards()) {
				LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>();
				map1.put(debitCard.getCard_no(), "Debit");
				list.add(map1);
			}
		}
	}

	public static List<HashMap<String, String>> convertToListOfTransaction(List<Transaction> trans) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (Transaction tran : trans) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("trans_id", "" + tran.getTrans_id());
			map.put("date", "Dummy date");
			map.put("sender", tran.getFrom_acc());
			map.put("reciver", tran.getTo_acc());
			map.put("type", tran.getType());
			map.put("amt", "" + tran.getAmount());
			list.add(map);
		}
		return list;
	}

	public static HashMap<String, String> getTransForExtAuthEntry(ExternalAuthorization extAuth) {
		Transaction trans = extAuth.getTransactions();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("trans_id", "" + trans.getTrans_id());
		map.put("from", trans.getTo_acc());
		map.put("to", trans.getFrom_acc());
		map.put("amt", "" + trans.getAmount());
		return map;
	}

	public static HashMap<String, String> getMapForPendingModify(ModifyUserInfo modify) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("idNo", escapeHtml(""+modify.getId()));
		map.put("user_name", escapeHtml(modify.getUser_name()));
		map.put("email", escapeHtml(modify.getEmail()));
		map.put("address", escapeHtml(modify.getAddress()));
		map.put("zipcode", escapeHtml(modify.getZipcode()));
		map.put("gender", escapeHtml(modify.getGender()));
		map.put("contact_no", escapeHtml(modify.getContact_no()));
		return map;
	}

	public static InternalUser updateEmpInfo(InternalUser username, Map<String, String> map) {
		username.setUser_name(escapeHtml(map.get("username")));
		username.setEmail(escapeHtml(map.get("email")));
		username.setAddress(escapeHtml(map.get("address")));
		username.setZipcode(escapeHtml(map.get("zipcode")));
		username.setGender(escapeHtml(map.get("gender")));
		username.setContact_no(escapeHtml(map.get("phone")));
		return username;
	}

}
