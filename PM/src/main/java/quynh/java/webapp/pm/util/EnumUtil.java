package quynh.java.webapp.pm.util;

import java.util.Arrays;

public class EnumUtil {
	public static String[] getNames(Class<? extends Enum<?>> e) {
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
