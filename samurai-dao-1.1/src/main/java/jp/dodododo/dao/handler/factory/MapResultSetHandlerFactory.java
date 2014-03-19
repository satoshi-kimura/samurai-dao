package jp.dodododo.dao.handler.factory;

import java.sql.Connection;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.handler.impl.MapResultSetHandler;
import jp.dodododo.dao.types.JavaType;

public class MapResultSetHandlerFactory extends ResultSetHandlerFactory<Map<String, Object>> {

	public static final MapResultSetHandlerFactory INSTANCE = new MapResultSetHandlerFactory();

	@Override
	public ResultSetHandler<Map<String, Object>> create(Class<Map<String, Object>> type, JavaType<?> javaType,
			IterationCallback<Map<String, Object>> callback, Dialect dialect, Map<String, Object> arg, Connection connection) {

		return new MapResultSetHandler(callback, arg);
	}

}
