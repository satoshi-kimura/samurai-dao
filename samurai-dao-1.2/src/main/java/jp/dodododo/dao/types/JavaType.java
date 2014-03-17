package jp.dodododo.dao.types;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JavaType<T> {
	T getValue(ResultSet rs, int columnIndex) throws SQLException;

	T getValue(ResultSet rs, String columnLabel) throws SQLException;

	T convert(Object value);

	T convert(Object value, String... formats);

	Class<T> getType();

	Class<?> getWrapperType();
}
