package jp.dodododo.dao.types;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.exception.PropertyNotFoundRuntimeException;
import jp.dodododo.dao.object.ObjectDesc;
import jp.dodododo.dao.object.ObjectDescFactory;
import jp.dodododo.dao.object.PropertyDesc;
import jp.dodododo.dao.util.EnumConverter;
import jp.dodododo.dao.util.TypesUtil;

public class TypeConverter {

	protected Object value;
	protected ObjectDesc<?> objectDesc;
	protected String[] formats;

	public TypeConverter(Object beanOrMap) {
		this(beanOrMap, DaoConfig.getDefaultConfig().getFormats());
	}

	public TypeConverter(Object beanOrMap, String... formats) {
		init(beanOrMap);
		this.formats = formats;
	}

	protected void init(Object o) {
		if (o == null) {
			this.value = Collections.emptyMap();
		} else {
			this.value = o;
		}
		initObjectDesc();
	}

	protected synchronized void initObjectDesc() {
		this.objectDesc = ObjectDescFactory.getObjectDesc(value);
	}

	public String getString(String key) {
		return getString(key, (String[]) null);
	}

	public String getString(String key, String format) {
		return getString(key, new String[] { format });
	}

	protected String getString(String key, String... formats) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.STRING.convert(val, formats);
	}

	protected Object getValue(String key) {
		try {
			return this.objectDesc.getPropertyDesc(key).getValue(value);
		} catch (PropertyNotFoundRuntimeException e) {
			initObjectDesc();
			try {
				return this.objectDesc.getPropertyDesc(key).getValue(value);
			} catch (PropertyNotFoundRuntimeException e1) {
				return null;
			}
		}
	}

	public boolean hasKey(String key) {
		try {
			PropertyDesc propertyDesc = objectDesc.getPropertyDesc(key);
			return propertyDesc.isReadable();
		} catch (PropertyNotFoundRuntimeException e) {
			return false;
		}
	}

	public boolean hasValue(String key) {
		try {
			Object value = getValue(key);
			return value != null;
		} catch (PropertyNotFoundRuntimeException e) {
			return false;
		}
	}

	public boolean isNull(String key) {
		return !hasValue(key);
	}

	public Number getNumber(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.NUMBER.convert(val);
	}

	public Short getShort(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.SHORT.convert(val);
	}

	public Byte getByte(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.BYTE.convert(val);
	}

	public Integer getInteger(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.INTEGER.convert(val);
	}

	public Long getLong(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.LONG.convert(val);
	}

	public Float getFloat(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.FLOAT.convert(val);
	}

	public Double getDouble(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.DOUBLE.convert(val);
	}

	public BigDecimal getBigDecimal(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.BIG_DECIMAL.convert(val);
	}

	public BigInteger getBigInteger(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.BIG_INTEGER.convert(val);
	}

	public Character getCharacter(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.CHARACTER.convert(val);
	}

	public Boolean getBoolean(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.BOOLEAN.convert(val);
	}

	public java.sql.Date getSqlDate(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.SQL_DATE.convert(val, formats);
	}

	public SQLXML getSQLXML(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.SQLXML.convert(val);
	}

	public Date getDate(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.DATE.convert(val, formats);
	}

	public Time getTime(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.TIME.convert(val, formats);
	}

	public Timestamp getTimestamp(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.TIMESTAMP.convert(val, formats);
	}

	public Calendar getCalendar(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.CALENDAR.convert(val, formats);
	}

	public InputStream getInputStream(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.INPUT_STREAM.convert(val);
	}

	public byte[] getBytes(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.BYTE_ARRAY.convert(val);
	}

	public Object getObject(String key) {
		Object val = getValue(key);
		if (val == null) {
			return null;
		}
		return JavaTypes.OBJECT.convert(val);
	}

	public <ENUM extends Enum<ENUM>> ENUM getEnum(String key, Class<ENUM> enumClass) {
		Object val = getValue(key);
		return EnumConverter.convert(val, enumClass);
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert(Object value, Class<T> to) {
		if (value == null && to.isPrimitive() == true) {
			value = 0;
		}
		return (T) TypesUtil.getJavaType(to).convert(value);
	}

	public Map<String, Object> toMap() {
		return objectDesc.toMap(value);
	}

	public void setFormats(String... formats) {
		this.formats = formats;
	}

	@Override
	public String toString() {
		return toMap().toString();
	}

	public static Value src(Object value) {
		return val(value);
	}
	public static Value val(Object value) {
		return new Value(value);
	}
}
