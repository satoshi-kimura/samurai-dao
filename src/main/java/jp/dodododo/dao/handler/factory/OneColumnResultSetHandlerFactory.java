package jp.dodododo.dao.handler.factory;

import java.sql.Connection;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.handler.impl.OneColumnResultSetHandler;
import jp.dodododo.dao.types.JavaType;

public class OneColumnResultSetHandlerFactory<TYPE> extends ResultSetHandlerFactory<TYPE> {

	@SuppressWarnings("rawtypes")
	public static final OneColumnResultSetHandlerFactory<?> INSTANCE = new OneColumnResultSetHandlerFactory();

	@Override
	public ResultSetHandler<TYPE> create(Class<TYPE> type, JavaType<?> javaType, IterationCallback<TYPE> callback, Dialect dialect,
			Map<String, Object> arg, Connection connection) {

		return new OneColumnResultSetHandler<TYPE>(callback, type, javaType);
	}

}
