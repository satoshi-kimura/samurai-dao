package jp.dodododo.dao.dialect.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import jp.dodododo.dao.error.SQLError;
import jp.dodododo.dao.util.WrapperUtil;
import jp.dodododo.dao.wrapper.PreparedStatementWrapper;

public class MySQLPreparedStatement extends PreparedStatementWrapper {

	public MySQLPreparedStatement(PreparedStatement preparedStatement) {
		super(preparedStatement);

		enableStreamingResults(preparedStatement);
	}

	protected void enableStreamingResults(PreparedStatement preparedStatement) {
		com.mysql.jdbc.Statement mySQLStatement = null;
		try {
			mySQLStatement = WrapperUtil.unwrap(preparedStatement, com.mysql.jdbc.Statement.class);
		} catch (Throwable ignore) {
		}
		if (mySQLStatement == null) {
			return;
		}

		try {
			mySQLStatement.enableStreamingResults();
		} catch (SQLException e) {
			throw new SQLError(e);
		}
	}

	@Override
	public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
		if(targetSqlType== Types.JAVA_OBJECT || targetSqlType== Types.OTHER) {
			super.setObject(parameterIndex, x);
		} else {
			super.setObject(parameterIndex, x, targetSqlType);
		}
	}
}
