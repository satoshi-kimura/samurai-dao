package jp.dodododo.dao.dialect.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import jp.dodododo.dao.dialect.TableNameResolver;
import jp.dodododo.dao.util.CacheUtil;
import jp.dodododo.dao.util.ConnectionUtil;
import jp.dodododo.dao.util.PreparedStatementUtil;
import jp.dodododo.dao.util.StringUtil;

public class MySQLTableNameResolver implements TableNameResolver {
    private static final List<String> TABLE_NAMES = CacheUtil.cacheList();
    private static final Map<String, String> TABLE_NAME_MAPS = CacheUtil.cacheMap();

	@Override
	public String resolve(Connection connection, String tableName) throws SQLException {
		String ret = TABLE_NAME_MAPS.get(tableName);
		if (ret != null) {
			return ret;
		}

        if (TABLE_NAMES.isEmpty()) {
            synchronized (TABLE_NAMES) {
                PreparedStatement ps = ConnectionUtil.prepareStatement(connection, "show tables");
                ResultSet rs = PreparedStatementUtil.executeQuery(ps);
                while (rs.next()) {
                    TABLE_NAMES.add(rs.getString(1));
                }
            }
        }

		for (String dbTableName : TABLE_NAMES) {
			if (Objects.equals(tableName, dbTableName)) {
                TABLE_NAME_MAPS.put(tableName, dbTableName);
				return dbTableName;
			}
		}
		for (String dbTableName : TABLE_NAMES) {
			if (StringUtil.equalsIgnoreCase(tableName, dbTableName)) {
                TABLE_NAME_MAPS.put(tableName, dbTableName);
				return dbTableName;
			}
		}

        TABLE_NAME_MAPS.put(tableName, tableName);
		return tableName;
	}
}
