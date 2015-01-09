package jp.dodododo.dao.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jp.dodododo.dao.exception.IORuntimeException;

public class TmpFileUtil {

	static private ThreadLocal<Set<File>> tmpFiles = new ThreadLocal<Set<File>>() {
		@Override
		protected java.util.Set<File> initialValue() {
			return new HashSet<File>();
		}
	};

	public static File createTempFile() {
		try {
			Set<File> tmpSet = tmpFiles.get();
			File tmpFile = File.createTempFile("samurai-dao-", ".tmp");
			tmpSet.add(tmpFile);
			tmpFile.deleteOnExit();
			return tmpFile;
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	public static void deleteThreadLocalTmpFiles() {
		Set<File> fileSet = tmpFiles.get();
		for (File file : fileSet) {
			@SuppressWarnings("unused")
			boolean delete = file.delete();
		}
		fileSet.clear();
		ThreadLocalUtil.remove(tmpFiles);
	}
}
