package jp.dodododo.dao.util.java;

import jp.dodododo.dao.annotation.Internal;
import jp.dodododo.dao.util.ClassUtil;

@Internal
public class Java6_0 {
	public static final int ROW_ID_SQL_TYPE = -8;

	public static Class<?> rowIdClass() {
		try {
			return ClassUtil.forName("java.sql.RowId");
		} catch (Throwable t) {
			return null;
		}
	}
}
