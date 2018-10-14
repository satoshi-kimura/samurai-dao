package jp.dodododo.dao.dialect.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import jp.dodododo.dao.wrapper.PreparedStatementWrapper;

public class MySQLPreparedStatement extends PreparedStatementWrapper {

	public MySQLPreparedStatement(PreparedStatement preparedStatement) {
		super(preparedStatement);
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
