package jp.dodododo.dao.exception;

import java.sql.SQLException;
import java.util.List;
import jp.dodododo.dao.log.SqlLog;
import jp.dodododo.dao.log.SqlLogRegistry;

public class SQLRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1169720528755756483L;

	public SQLRuntimeException(String message, SqlLogRegistry sqlLogRegistry, SQLException cause) {
		this(message + getState(cause) + getOtherThreadInfo(sqlLogRegistry), cause);
	}

	public SQLRuntimeException(SqlLogRegistry sqlLogRegistry, SQLException cause) {
		this("", sqlLogRegistry, cause);
	}

	public SQLRuntimeException(SQLException cause) {
		this("", cause);
	}

	public SQLRuntimeException(String message, SQLException cause) {
		super(message, cause);
	}

	@Override
	public SQLException getCause() {
		return (SQLException) super.getCause();
	}

	public int getErrorCode() {
		return getCause().getErrorCode();
	}

	public String getSQLState() {
		return getCause().getSQLState();
	}

	private static String getState(SQLException cause) {
		return " [ SQLState=" + cause.getSQLState() + ", ErrorCode=" + cause.getErrorCode() + " ] ";
	}

	private static String getOtherThreadInfo(SqlLogRegistry sqlLogRegistry) {
		StringBuilder ret = new StringBuilder(256);
		ret.append(System.lineSeparator());
		ret.append("[Latest SQL of other threads : ");
		ret.append(System.lineSeparator());

		List<SqlLog> logs = sqlLogRegistry.getRunningSqlLogs(Thread.currentThread());
		logs.forEach(log -> {
			ret.append("  ");
			ret.append("[").append(log.getThreadName()).append("]");
			ret.append("[").append(log.getCompleteSql()).append("]");
			ret.append(System.lineSeparator());
		});

		ret.append("]");

		return ret.toString();
	}

}
