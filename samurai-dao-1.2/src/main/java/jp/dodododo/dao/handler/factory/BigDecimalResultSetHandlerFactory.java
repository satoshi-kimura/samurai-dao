package jp.dodododo.dao.handler.factory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.handler.impl.BigDecimalResultSetHandler;
import jp.dodododo.dao.types.JavaType;

public class BigDecimalResultSetHandlerFactory extends ResultSetHandlerFactory<BigDecimal> {

	public static final BigDecimalResultSetHandlerFactory INSTANCE = new BigDecimalResultSetHandlerFactory();

	@Override
	public ResultSetHandler<BigDecimal> create(Class<BigDecimal> type, JavaType<?> javaType, IterationCallback<BigDecimal> callback, Dialect dialect,
			Map<String, Object> arg, Connection connection) {

		return new BigDecimalResultSetHandler(callback, dialect);
	}

}
