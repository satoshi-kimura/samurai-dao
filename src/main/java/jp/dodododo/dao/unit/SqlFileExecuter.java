package jp.dodododo.dao.unit;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.handler.ResultSetHandler;
import jp.dodododo.dao.handler.impl.MapResultSetHandler;
import jp.dodododo.dao.impl.EmptyIterationCallback;
import jp.dodododo.dao.impl.RdbDao;
import jp.dodododo.dao.util.DaoUtil;
import jp.dodododo.dao.value.ParameterValue;

public class SqlFileExecuter {
	private Connection connection;

	public SqlFileExecuter(Connection connection) {
		this.connection = connection;
	}

	public List<Map<String, Object>> executeSelect(String sqlFilePath) {
		RdbDao dao = new RdbDao(connection);
		IterationCallback<Map<String, Object>> callback = EmptyIterationCallback.getInstance();
		ResultSetHandler<Map<String, Object>> handler = new MapResultSetHandler(callback);
		Map<String, Object> arg = DaoUtil.args();
		return dao.select(sqlFilePath, arg, callback, handler, false);
	}

	public void executeUpdate(String sqlFilePath) {
		RdbDao daoImpl = new RdbDao(connection);
		Map<String, ParameterValue> values = new HashMap<>();
		daoImpl.executeUpdate(values, sqlFilePath, null, false);
	}

	public void executeInsert(String sqlFilePath) {
		executeUpdate(sqlFilePath);
	}

	public void executeDelete(String sqlFilePath) {
		executeUpdate(sqlFilePath);
	}
}
