package jp.dodododo.dao.sql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.dodododo.dao.DaoConstants;
import jp.dodododo.dao.dialect.Dialect;
import jp.dodododo.dao.object.ObjectDesc;
import jp.dodododo.dao.object.ObjectDescFactory;
import jp.dodododo.dao.util.DaoUtil;
import jp.dodododo.dao.value.ParameterValue;

public class SqlContext implements Serializable {
	private static final long serialVersionUID = 9205994213162637655L;

	private String tableName;
	private List<ParameterValue> pks;
	private List<ParameterValue> columns;
	private List<Object> values;
	private Class<?> queryClass;
	private Dialect dialect;
	private Map<String, Object> parameters = new HashMap<String, Object>();

	public SqlContext(String tableName, List<ParameterValue> columns, Class<?> queryClass, Dialect dialect) {
		this(tableName, null, columns, queryClass, dialect);
	}

	public SqlContext(String tableName, List<ParameterValue> pks, List<ParameterValue> columns, Class<?> queryClass,
			Dialect dialect) {
		this.tableName = tableName;
		this.pks = pks == null ? new ArrayList<ParameterValue>() : pks;
		this.columns = columns == null ? new ArrayList<ParameterValue>() : columns;
		this.queryClass = queryClass;
		this.dialect = dialect;
		init();
	}

	private void init() {
		addParameter(DaoConstants.ORDER_BY, Collections.EMPTY_LIST);
		for (ParameterValue parameterValue : columns) {
			String name = parameterValue.getName();
			Object value = parameterValue.getValue();
			parameters.put(name, value);
		}
	}

	public String getTableName() {
		return tableName;
	}

	public List<ParameterValue> getPks() {
		return pks;
	}

	public List<ParameterValue> getColumns() {
		return columns;
	}

	public Dialect getDialect() {
		return dialect;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void addParameter(Map<String, Object> parameters) {
		this.parameters.putAll(parameters);
	}

	public void addParameter(String name, Object value) {
		this.parameters.put(name, value);
	}

	public Class<?> getQueryClass() {
		return queryClass;
	}

	public List<?> getVals() {
		return values;
	}

	public void setValues(List<?> values) {
		this.values = new ArrayList<Object>();
		if (values == null || values.isEmpty()) {
			return;
		}
		addParameter(DaoUtil.VALUES, values);
		ObjectDesc<?> objectDesc = ObjectDescFactory.getObjectDesc(values.get(0));
		for (Object object : values) {
			Object map = objectDesc.toMap(object);
			this.values.add(map);
		}
	}
}
