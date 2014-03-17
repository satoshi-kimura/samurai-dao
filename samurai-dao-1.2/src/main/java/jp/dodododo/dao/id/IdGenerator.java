package jp.dodododo.dao.id;

import java.sql.Connection;

import jp.dodododo.dao.dialect.Dialect;

public interface IdGenerator {
	boolean isPrepare(Connection connection);

	boolean isPrepare(Dialect dialect);

	Object generate(Connection connection, String sequenceName);

	Object generate(Connection connection, Dialect dialect, String sequenceName);

}
