package jp.dodododo.dao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jp.dodododo.dao.exception.IORuntimeException;

public class FileInputStreamUtil {

	public static FileInputStream newFileInputStream(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IORuntimeException(e);
		}
	}
}
