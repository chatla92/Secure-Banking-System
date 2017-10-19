package util;

public class RoleCheck {

	public static boolean isInternal(String role) {
		return role.equals("T1") || role.equals("T2") || role.equals("IA");
	}

	public static boolean isExternal(String role) {
		return role.equals("Ind") || role.equals("MR");
	}
}