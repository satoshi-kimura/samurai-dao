package jp.dodododo.dao.util;

public class ThreadLocalUtil {

	public static <T> void remove(ThreadLocal<T> threadLocal) {
		threadLocal.set(null);
		threadLocal.remove();
	}
}
