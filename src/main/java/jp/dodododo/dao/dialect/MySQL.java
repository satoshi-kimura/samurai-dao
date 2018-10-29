package jp.dodododo.dao.dialect;

import java.sql.Connection;
import java.sql.PreparedStatement;

import jp.dodododo.dao.dialect.mysql.MySQLPreparedStatement;
import jp.dodododo.dao.dialect.mysql.MySQLTableNameResolver;
import jp.dodododo.dao.metadata.TableMetaData;
import jp.dodododo.dao.object.PropertyDesc;
import jp.dodododo.dao.paging.LimitOffset;
import jp.dodododo.dao.types.TypeConverter;
import jp.dodododo.dao.util.ConnectionUtil;

public class MySQL extends Standard {

	@Override
	public String limitOffsetSql(String originalSQL, LimitOffset limitOffset) {
		return super.limitOffsetSql(originalSQL, limitOffset);
	}

	@Override
	public String getSuffix() {
		return "_mysql";
	}

	@Override
	public String identitySelectSql() {
		return "SELECT LAST_INSERT_ID()";
	}

	@Override
	public boolean isPrepareIdentitySelectSql() {
		return false;
	}

	@Override
	public PreparedStatement preparedStatement(PreparedStatement ps) {
		return new MySQLPreparedStatement(ps);
	}

	@Override
	public void setId(Object entity, PropertyDesc propertyDesc, Object idValue) {
		if (idValue == null) {
			return;
		}
		if (TypeConverter.convert(idValue, Integer.TYPE) == 0) {
			return;
		}
		try {
			if (TypeConverter.convert(propertyDesc.getValue(entity, true), Integer.TYPE) != 0) {
				return;
			}
		} catch (RuntimeException ignore) {
		}
		propertyDesc.setValue(entity, idValue);
	}

	@Override
	public String getReplaceCommand() {
		return "INSERT";
	}

	@Override
	public String getReplaceOption() {
		return "ON DUPLICATE KEY UPDATE";
	}

	@Override
	public String getSchema(Connection connection) {
		return ConnectionUtil.getCatalog(connection);
	}

	@Override
	public String getSchema(TableMetaData metaData) {
		return metaData.getCatalog();
	}
	
	@Override
	public TableNameResolver getTableNameResolver() {
		return new MySQLTableNameResolver();
	}
}
