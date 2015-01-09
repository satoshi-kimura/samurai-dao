package jp.dodododo.dao.exception;

import java.io.IOException;

public class IORuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IORuntimeException(String message, IOException cause) {
		super(message, cause);
	}

	public IORuntimeException(IOException cause) {
		super(cause);
	}

}
