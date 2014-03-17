package jp.dodododo.dao.util;

import org.apache.commons.lang.SystemUtils;

/**
 * <pre>
 * The attributes are:
 * 0 - reset everything
 * 1 - bright (bold)
 * 2 - dim
 * 3 - underline (didn't for me, but some worked for some)
 * 4 - underline (worked for me)
 * 5 - blink
 * 6 - nothing
 * 7 - reverse (color scheme, not the text)
 * 8 - hidden
 *
 * The foreground colors are:
 * 30 - black
 * 31 - red
 * 32 - green
 * 33 - yellow
 * 34 - blue
 * 35 - magenta
 * 36 - cyan
 * 37 - white
 * </pre>
 */
public class ConsoleColor {

	public static final String BLACK = "0;30";
	public static final String DARK_GRAY = "1;30";
	public static final String BLUE = "0;34";
	public static final String LIGHT_BLUE = "1;34";
	public static final String GREEN = "0;32";
	public static final String LIGHT_GREEN = "1;32";
	public static final String CYAN = "0;36";
	public static final String LIGHT_CYAN = "1;36";
	public static final String RED = "0;31";
	public static final String LIGHT_RED = "1;31";
	public static final String PURPLE = "0;35";
	public static final String LIGHT_PURPLE = "1;35";
	public static final String BROWN = "0;33";
	public static final String YELLOW = "1;33";
	public static final String LIGHT_GRAY = "0;37";
	public static final String WHITE = "1;37";

	public static String toLightRed(String message) {
		return addColor(message, LIGHT_RED);
	}

	public static String addColor(String message, String color) {
		if (SystemUtils.IS_OS_UNIX) {
			return "\u001b[" + color + "m" + message + "\u001b[m";
		}
		return message;
	}
}
