package jp.dodododo.dao.handler.factory;

import java.sql.Connection;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.handler.impl.RowResultSetHandler;
import jp.dodododo.dao.row.Row;
import jp.dodododo.dao.types.JavaType;

public class RowResultSetHandlerFactory extends ResultSetHandlerFactory<Row> {

	public static final RowResultSetHandlerFactory INSTANCE = new RowResultSetHandlerFactory();

	@Override
	public ResultSetHandler<Row> create(Class<Row> type, JavaType<?> javaType, IterationCallback<Row> callback, Dialect dialect,
			Map<String, Object> arg, Connection connection) {

		return new RowResultSetHandler(callback);
	}

}
