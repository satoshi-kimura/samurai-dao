package jp.dodododo.dao.handler.factory;

import java.sql.Connection;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.handler.impl.EnumResultSetHandler;
import jp.dodododo.dao.types.JavaType;

public class EnumResultSetHandlerFactory<ENUM extends Enum<?>> extends ResultSetHandlerFactory<ENUM> {

	@SuppressWarnings("rawtypes")
	public static final EnumResultSetHandlerFactory<?> INSTANCE = new EnumResultSetHandlerFactory();

	@Override
	public ResultSetHandler<ENUM> create(Class<ENUM> type, JavaType<?> javaType, IterationCallback<ENUM> callback, Dialect dialect,
			Map<String, Object> arg, Connection connection) {

		return new EnumResultSetHandler<ENUM>(callback, type, dialect);
	}
}
