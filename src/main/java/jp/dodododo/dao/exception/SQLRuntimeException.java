package jp.dodododo.dao.exception;

import java.sql.SQLException;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.properties.SamuraiDaoProperties;

public class SQLRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1169720528755756483L;

	{
		boolean enableSqlDump = SamuraiDaoProperties.enableSqlDump();
		if (enableSqlDump == true) {
			SqlLogRegistry.getInstance().dump();
		}
	}
	public SQLRuntimeException(String message, SQLException cause) {
		super(message + getState(cause) , cause);
	}

	public SQLRuntimeException(SQLException cause) {
		this("", cause);
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
}
