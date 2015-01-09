package jp.dodododo.dao.util;

import java.io.Closeable;
import java.io.IOException;

import jp.dodododo.dao.exception.IORuntimeException;

/**
 *
 * @author Satoshi Kimura
 */
public abstract class CloseableUtil {

	public static void close(Closeable closeable) {
		close(closeable, false);
	}

	public static void close(Closeable closeable, boolean ignoreException) {
		if (closeable == null) {
			return;
		}
		try {
			closeable.close();
		} catch (IOException e) {
			if (ignoreException == false) {
				throw new IORuntimeException(e);
			}
		}
	}

}
