package jp.dodododo.dao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import jp.dodododo.dao.exception.IORuntimeException;

/**
 *
 * @author Satoshi Kimura
 */
public abstract class ReaderUtil {
	public static String readText(Reader reader) {
		BufferedReader in = new BufferedReader(reader);
		StringBuilder out = new StringBuilder(100);
		try {
			try {
				char[] buf = new char[1024];
				int n;
				while ((n = in.read(buf)) >= 0) {
					out.append(buf, 0, n);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		return out.toString();
	}
}
