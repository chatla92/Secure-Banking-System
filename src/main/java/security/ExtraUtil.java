package security;

import java.util.HashMap;
import java.util.Map;

import hibernateModel.ExternalUser;
import hibernateModel.InternalUser;

public class ExtraUtil {

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

}
