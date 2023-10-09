package quynh.java.webapp.pm.util;

public class StringChecker {
	public static boolean isInteger(String s) {
		if (s == null || s.isBlank())
			return false;
		int i = 0;
		if (s.charAt(0) == '-') {
			if (s.length() == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < s.length(); i++) {
			if (s.charAt(i) < '0' || s.charAt(i) > '9')
				return false;
		}
		return true;
	}
}
