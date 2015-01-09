package jp.dodododo.dao.message;

import static jp.dodododo.dao.util.EmptyUtil.*;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import jp.dodododo.dao.annotation.Internal;

@Internal
public class Message {
	private static ResourceBundle bundle = ResourceBundle.getBundle("jp/dodododo/dao/message");

	public static String getMessage(String key, Object... args) {
		String text = bundle.getString(key);
		if (isEmpty(args) == true) {
			return text;
		}
		return MessageFormat.format(text, args);
	}

}
