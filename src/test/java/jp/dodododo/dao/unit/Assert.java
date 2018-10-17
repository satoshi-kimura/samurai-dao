package jp.dodododo.dao.unit;

import jp.dodododo.dao.util.StringUtil;
import junit.framework.ComparisonFailure;

public class Assert {

	public static void assertMatches(String message, String pattern, String actual) {
		if (actual.matches(pattern) == false) {
			throw new ComparisonFailure(message, pattern, actual);
		}
	}

	public static void assertMatches(String pattern, String actual) {
		assertMatches(null, pattern, actual);
	}
	
	public static void assertEqualsIgnoreCase(String message, String expected, String actual) {
		if(StringUtil.equalsIgnoreCase(expected, actual) == false) {
			throw new ComparisonFailure(message, expected, actual);
		}
	}
	
	public static void assertEqualsIgnoreCase(String expected, String actual) {
		assertEqualsIgnoreCase(null, expected, actual);
	}

}
