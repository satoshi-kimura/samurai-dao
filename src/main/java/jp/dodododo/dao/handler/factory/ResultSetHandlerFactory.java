package jp.dodododo.dao.handler.factory;

import java.sql.Connection;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.exception.UnsupportedTypeException;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.row.Row;
import jp.dodododo.dao.types.JavaType;
import jp.dodododo.dao.types.JavaTypes;
import jp.dodododo.dao.types.SQLType;
import jp.dodododo.dao.types.SQLTypes;
import jp.dodododo.dao.util.CacheUtil;
import jp.dodododo.dao.util.TypesUtil;

public abstract class ResultSetHandlerFactory<T> {
	protected static final Map<Class<?>, ResultSetHandlerFactory<?>> FACTORY_CACHE = CacheUtil.cacheMap();

	public abstract ResultSetHandler<T> create(Class<T> type, JavaType<?> javaType, IterationCallback<T> callback, Dialect dialect, Map<String, Object> arg, Connection connection);

	@SuppressWarnings({ "unchecked" })
	public static <TYPE> ResultSetHandlerFactory<TYPE> getResultSetHandlerFactory(Class<TYPE> type) {

		ResultSetHandlerFactory<?> ret = FACTORY_CACHE.get(type);

		if (ret != null) {
			return (ResultSetHandlerFactory<TYPE>) ret;
		}

		if (Row.class.isAssignableFrom(type) == true) {
			ret = RowResultSetHandlerFactory.INSTANCE;
		} else if (Map.class.isAssignableFrom(type) == true) {
			ret = MapResultSetHandlerFactory.INSTANCE;
		}

		if (ret != null) {
			put(type, ret);
			return (ResultSetHandlerFactory<TYPE>) ret;
		}

		JavaType<?> javaType = TypesUtil.getJavaType(type);
		SQLType sqlType = TypesUtil.getSQLType(type);

		if (type.isEnum() == true) {
			ret = EnumResultSetHandlerFactory.INSTANCE;
		}else if (JavaTypes.OBJECT.equals(javaType) == true) {
			ret = BeanResultSetHandlerFactory.INSTANCE;
		} else if (JavaTypes.BIG_DECIMAL.equals(javaType) == true) {
			ret = BigDecimalResultSetHandlerFactory.INSTANCE;
		} else if (SQLTypes.OBJECT.equals(sqlType) == true) {
			throw new UnsupportedTypeException(type);
		} else {
			ret = OneColumnResultSetHandlerFactory.INSTANCE;
		}
		put(type, ret);
		return (ResultSetHandlerFactory<TYPE>) ret;
	}

	public static <TYPE> void put(Class<TYPE> type, ResultSetHandlerFactory<?> ret) {
		FACTORY_CACHE.put(type, ret);
	}
}
