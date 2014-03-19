package jp.dodododo.dao.util;

import java.io.IOException;
import java.io.InputStream;

import jp.dodododo.dao.exception.IORuntimeException;

/**
 *
 * @author Satoshi Kimura
 */
public abstract class InputStreamUtil {
	public static int available(InputStream is) {
		try {
			return is.available();
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}
}