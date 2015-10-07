package jp.dodododo.dao.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;

import jp.dodododo.dao.io.AutoCloseFileInputStream;

public class FileStreamUtil {
	public static OutputStream newOutputStream(File file) {
		return newFileOutputStream(file);
	}

	public static FileOutputStream newFileOutputStream(File file) {
		try {
			return new FileOutputStream(file);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static InputStream newInputStream(File file) {
		return new AutoCloseFileInputStream(file);
	}
}
