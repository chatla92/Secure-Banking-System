package security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import hibernateModel.Accounts;
import hibernateModel.CreditCards;
import hibernateModel.DebitCards;
import hibernateModel.ExternalUser;
import hibernateModel.InternalUser;
import hibernateModel.Transaction;

public class ModelUtil {

	public static Map<String, String> getUserInfoAsMap(ExternalUser user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", user.getUser_name());
		map.put("address", user.getAddress());
		map.put("phone", user.getContact_no());
		map.put("zipcode", user.getZipcode());
		map.put("email", user.getEmail());
		map.put("gender", user.getGender());
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

	public static ExternalUser updateUserInfo(ExternalUser user, Map<String, String> map) {
		user.setUser_name(map.get("username"));
		user.setAddress(map.get("address"));
		user.setContact_no(map.get("phone"));
		user.setZipcode(map.get("zipcode"));
		user.setEmail(map.get("email"));
		user.setGender(map.get("gender"));
		return user;
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
			map.put("date", "Dummy date");
			map.put("reciver", tran.getTo_acc());
			map.put("type", tran.getType());
			map.put("amt", "" + tran.getAmount());
			list.add(map);
		}
		return list;
	}

}