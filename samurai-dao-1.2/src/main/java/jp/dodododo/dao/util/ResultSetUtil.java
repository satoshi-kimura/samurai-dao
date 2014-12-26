package jp.dodododo.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.dodododo.dao.error.SQLError;

/**
 *
 * @author Satoshi Kimura
 */
public abstract class ResultSetUtil {

	public static void close(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			throw new SQLError(e);
		}
	}

	public static Object getObject(ResultSet rs, String columnLabel) {
		try {
			return rs.getObject(columnLabel);
		} catch (SQLException e) {
			throw new SQLError(e);
		}
	}
}
