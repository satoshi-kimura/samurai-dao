package jp.dodododo.dao.types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZoneOffset;

public interface JavaType<T> {
	T getValue(ResultSet rs, int columnIndex) throws SQLException;

	T getValue(ResultSet rs, String columnLabel) throws SQLException;

	T convert(Object value);

	T convert(Object value, ZoneId zoneId);

	T convert(Object value, ZoneOffset zoneOffset);

	T convert(Object value, String... formats);

	T convert(Object value, ZoneId zoneId, String... formats);

	T convert(Object value, ZoneOffset zoneOffset, String... formats);

	Class<T> getType();

	Class<?> getWrapperType();
}
