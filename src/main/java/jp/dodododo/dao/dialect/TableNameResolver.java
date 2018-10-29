package jp.dodododo.dao.dialect;

import java.sql.Connection;
import java.sql.SQLException;

public interface TableNameResolver {
	default String resolve(Connection connection, String tableName) throws SQLException {
		return tableName;
	}
}
