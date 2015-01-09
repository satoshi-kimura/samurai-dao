package jp.dodododo.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.columns.ResultSetColumn;
import jp.dodododo.dao.util.CaseInsensitiveMap;

public class MapResultSetHandler extends AbstractResultSetHandler<Map<String, Object>> {

	protected Map<String, Object> arg;

	protected MapResultSetHandler() {
	}

	public MapResultSetHandler(IterationCallback<Map<String, Object>> callback) {
		this(callback, null);
	}

	public MapResultSetHandler(IterationCallback<Map<String, Object>> callback, Map<String, Object> arg) {
		super(callback);
		this.arg = arg;
	}

	@Override
	protected Map<String, Object> createRow(ResultSet rs, List<ResultSetColumn> resultSetColumnList) throws SQLException {
		Map<String, Object> row = newInstance();

		for (Iterator<ResultSetColumn> iter = resultSetColumnList.iterator(); iter.hasNext();) {
			ResultSetColumn column = iter.next();
			String name = column.getName();
			Object value = rs.getObject(name);
			row.put(name, value);
		}
		return row;
	}

	protected Map<String, Object> newInstance() {
		CaseInsensitiveMap<Object> ret = new CaseInsensitiveMap<Object>();
		ret.putAll(arg);
		return ret;
	}
}
