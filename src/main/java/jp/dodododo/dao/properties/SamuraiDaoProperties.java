package jp.dodododo.dao.properties;

import java.util.Properties;
import jp.dodododo.dao.types.JavaTypes;
import jp.dodododo.dao.util.PropertiesUtil;

public class SamuraiDaoProperties {
	private static Properties properties;

	static {
		try {
			properties = PropertiesUtil.getProperties("samurai-dao.properties");
		} catch (RuntimeException e) {
			properties = new Properties();
		}
	}

	public static boolean enableSqlDump() {
		Boolean enable = JavaTypes.BOOLEAN.convert(properties.getProperty("enable_sql_dump"));
		if(enable == null) {
			return false;
		}
		return enable;
	}
}
