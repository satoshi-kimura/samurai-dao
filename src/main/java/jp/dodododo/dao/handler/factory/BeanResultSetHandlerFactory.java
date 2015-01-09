package jp.dodododo.dao.handler.factory;

import java.sql.Connection;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.handler.impl.BeanResultSetHandler;
import jp.dodododo.dao.types.JavaType;

public class BeanResultSetHandlerFactory<BEAN> extends ResultSetHandlerFactory<BEAN> {

	@SuppressWarnings("rawtypes")
	public static final BeanResultSetHandlerFactory<?> INSTANCE = new BeanResultSetHandlerFactory();

	@Override
	public ResultSetHandler<BEAN> create(Class<BEAN> type, JavaType<?> javaType, IterationCallback<BEAN> callback, Dialect dialect,
			Map<String, Object> arg, Connection connection) {

		return new BeanResultSetHandler<BEAN>(callback, arg, type, connection);
	}

}
