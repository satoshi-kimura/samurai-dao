package jp.dodododo.dao.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SlowQuery {
	private static final Log logger = LogFactory.getLog(SlowQuery.class);

	public static void warn(double time, String slowQuerySql) {
		String caller = "";
		try {
			throw new Exception();
		} catch (Exception e) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (StackTraceElement element : stackTrace) {
				String className = element.getClassName();
				String methodName = element.getMethodName();
				int lineNumber = element.getLineNumber();
				if (className.startsWith("jp.dodododo.dao") == false) {
					caller = className + "#" + methodName + "()" + " line:" + lineNumber;
					break;
				}
			}
		}
		logger.warn("QueryTime: " + time + "secs. SQL: " + slowQuerySql + ". Caller: " + caller);
	}
}
