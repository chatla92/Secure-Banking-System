package util;

public class RoleCheck {

	public static boolean isInternal(String role) {
		return role.equalsIgnoreCase("Tier1") || role.equalsIgnoreCase("Tier2") || role.equalsIgnoreCase("IA");
	}

	public static boolean isExternal(String role) {
		return role.equalsIgnoreCase("Ind") || role.equalsIgnoreCase("MR");
	}
}