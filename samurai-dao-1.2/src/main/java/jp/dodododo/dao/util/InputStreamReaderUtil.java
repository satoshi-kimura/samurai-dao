package jp.dodododo.dao.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jp.dodododo.dao.exception.IORuntimeException;

/**
 *
 * @author Satoshi Kimura
 */
public abstract class InputStreamReaderUtil {

	public static InputStreamReader create(InputStream is, String encoding) {
		try {
			return new InputStreamReader(is, encoding);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}
}