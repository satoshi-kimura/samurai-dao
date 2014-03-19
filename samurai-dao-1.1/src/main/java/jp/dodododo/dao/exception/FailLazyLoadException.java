package jp.dodododo.dao.exception;

import jp.dodododo.dao.message.Message;
import jp.dodododo.dao.object.ObjectDescFactory;

public class FailLazyLoadException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FailLazyLoadException(Object o, Throwable cause) {
		super(Message.getMessage("00045", toString(o)), cause);
	}

	public FailLazyLoadException(Object o) {
		super(Message.getMessage("00045", toString(o)));
	}

	private static String toString(Object o) {
		String simpleClassName = o.getClass().getSimpleName();
		try {
			return simpleClassName + "#" + ObjectDescFactory.getObjectDesc(o).toMapWithFields(o).toString();
		} catch (Throwable ignore) {
			return simpleClassName;
		}
	}

}
