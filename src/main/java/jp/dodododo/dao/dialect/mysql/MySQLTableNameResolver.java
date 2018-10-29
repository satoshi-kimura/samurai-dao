package jp.dodododo.dao.dialect.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.dodododo.dao.dialect.TableNameResolver;
import jp.dodododo.dao.util.ConnectionUtil;
import jp.dodododo.dao.util.PreparedStatementUtil;
import jp.dodododo.dao.util.StringUtil;

public class MySQLTableNameResolver implements TableNameResolver {
	@Override
	public String resolve(Connection connection, String tableName) throws SQLException {
		PreparedStatement ps = ConnectionUtil.prepareStatement(connection, "show tables");
		ResultSet rs = PreparedStatementUtil.executeQuery(ps);
		while (rs.next()) {
			String dbTableName = rs.getString(1);
			if (StringUtil.equalsIgnoreCase(tableName, dbTableName)) {
				return dbTableName;
			}
		}
		return tableName;
	}
}
