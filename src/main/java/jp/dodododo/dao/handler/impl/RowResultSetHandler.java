package jp.dodododo.dao.handler.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.columns.ResultSetColumn;
import jp.dodododo.dao.row.Row;

public class RowResultSetHandler extends AbstractResultSetHandler<Row> {

	protected MapResultSetHandler delegate = new MapResultSetHandler();

	public RowResultSetHandler(IterationCallback<Row> callback) {
		super(callback);
	}

	@Override
	protected Row createRow(ResultSet rs, List<ResultSetColumn> resultSetColumnList) throws SQLException {

		Map<String, Object> row = delegate.createRow(rs, resultSetColumnList);
		if (row == null) {
			return null;
		}
		return new Row(row);
	}
}
