package jp.dodododo.dao.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import jp.dodododo.dao.message.Message;
import jp.dodododo.dao.util.ClassUtil;
import jp.dodododo.dao.util.ConnectionUtil;
import jp.dodododo.dao.util.DataSourceUtil;
import jp.dodododo.dao.util.DatabaseMetaDataUtil;
import jp.dodododo.dao.util.PropertiesUtil;
import jp.dodododo.dao.wrapper.ConnectionWrapper;

public abstract class DialectManager {

	private static Properties dialectClassNames;

	private static Map<String, Dialect> dialectInstances = Collections.synchronizedMap(new HashMap<String, Dialect>());

	static {
		initDefaultDialectClassNames();
	}

	public static void register(String productName, String className) {
		dialectClassNames.put(productName, className);
	}

	private static void initDefaultDialectClassNames() {
		RuntimeException cause = null;
		for (int i = 0; i < 10; i++) {
			try {
				dialectClassNames = PropertiesUtil.getProperties("jp/dodododo/dao/dialect.properties");
				return;
			} catch (RuntimeException e) {
				cause = e;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException ignore) {
			}
		}
		throw cause;
	}

	public static Dialect getDialect(DataSource dataSource) {
		Connection connection = null;
		try {
			connection = DataSourceUtil.getConnection(dataSource);
			return getDialect(connection);
		} finally {
			ConnectionUtil.close(connection);
		}
	}

	public static Dialect getDialect(Connection connection) {
		if (connection instanceof ConnectionWrapper) {
			connection = ((ConnectionWrapper) connection).getOriginalConnection();
		}
		DatabaseMetaData dmd = ConnectionUtil.getMetaData(connection);
		return getDialect(dmd);
	}

	public static Dialect getDialect(DatabaseMetaData dmd) {
		String productName = DatabaseMetaDataUtil.getDatabaseProductName(dmd);
		String productVersion = DatabaseMetaDataUtil.getDatabaseProductVersion(dmd);
		int majorVersion = DatabaseMetaDataUtil.getDatabaseMajorVersion(dmd);
		int minorVersion = DatabaseMetaDataUtil.getDatabaseMinorVersion(dmd);
		return getDialect(productName, productVersion, majorVersion, minorVersion);
	}

	public static Dialect getDialect(String productName, String productVersion, int majorVersion, int minorVersion) {
		Dialect dialect = dialectInstances.get(productName + productVersion);
		if (dialect != null) {
			return dialect;
		}
		dialect = dialectInstances.get(productName);
		if (dialect != null) {
			return dialect;
		}
		String className = dialectClassNames.getProperty(productName + productVersion);
		if (className == null) {
			Set<Entry<Object, Object>> entrySet = dialectClassNames.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				if (productName.startsWith(entry.getKey().toString()) == true) {
					className = entry.getValue().toString();
					break;
				}
			}
		}
		if (className == null) {
			throw new UnsupportedOperationException(Message.getMessage("00014", productName));
		}

		Class<? extends Dialect> clazz = ClassUtil.forName(className);
		dialect = ClassUtil.newInstance(clazz);
		dialectInstances.put(productName + productVersion, dialect);
		dialectInstances.put(productName, dialect);
		return dialect;
	}

	public static void addDialect(String productName, Dialect dialect) {
		dialectInstances.put(productName, dialect);
	}
}
