package jp.dodododo.dao.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import java.util.Set;
import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.util.EmptyUtil;
import jp.dodododo.dao.util.ThreadLocalUtil;

public class SqlLogRegistry {

	protected DaoConfig config;

	private static final SqlLogRegistry INSTANCE = new SqlLogRegistry();

	private ThreadLocal<LinkedList<SqlLog>> threadList = ThreadLocal.withInitial(() -> new LinkedList<>());

	private Map<Thread, LinkedList<SqlLog>> allLogs = new HashMap<>();

	public static SqlLogRegistry getInstance() {
		return INSTANCE;
	}

	/**
	 * デフォルトの上限サイズを使用してインスタンスを構築します。
	 *
	 */
	public SqlLogRegistry() {
		this(DaoConfig.getDefaultConfig());
	}

	/**
	 * DaoConfigを指定してインスタンスを構築します。
	 *
	 * @param config
	 *            config
	 */
	public SqlLogRegistry(DaoConfig config) {
		setConfig(config);
	}

	public void setConfig(DaoConfig config) {
		this.config = config;
	}

	public DaoConfig getConfig() {
		return config;
	}

	public int getLimitSize() {
		return config.getLogMaxSize();
	}

	public int getSize() {
		return getSqlLogList().size();
	}

	public boolean isEmpty() {
		return EmptyUtil.isEmpty(getSqlLogList());
	}

	public SqlLog get(int index) {
		return getSqlLogList().get(index);
	}

	public SqlLog getLast() {
		return isEmpty() ? null : getSqlLogList().getLast();
	}

	public void add(SqlLog sqlLog) {
		LinkedList<SqlLog> list = getSqlLogList();
		list.add(sqlLog);
		allLogs.put(sqlLog.getExecuteThread(), list);
		if (getLimitSize() < list.size()) {
			list.removeFirst();
		}
	}

	public void clear() {
		getSqlLogList().clear();
		ThreadLocalUtil.remove(threadList);
	}

	private LinkedList<SqlLog> getSqlLogList() {
		LinkedList<SqlLog> ret = threadList.get();
		if (ret == null) {
			ret = new LinkedList<>();
			threadList.set(ret);
		}
		return ret;
	}

	public List<String> getSqls(ExecuteType executeType) {
		LinkedList<SqlLog> sqlLogList = getSqlLogList();
		List<String> ret = new ArrayList<>(sqlLogList.size());
		for (SqlLog sqlLog : sqlLogList) {
			if (ExecuteType.ALL.equals(executeType) || executeType.equals(sqlLog.getSqlType())) {
				ret.add(sqlLog.getCompleteSql());
			}
		}
		return ret;
	}

	public List<String> getAllSql() {
		LinkedList<SqlLog> sqlLogList = getSqlLogList();
		List<String> ret = new ArrayList<>(sqlLogList.size());
		for (SqlLog sqlLog : sqlLogList) {
			ret.add(sqlLog.getCompleteSql());
		}
		return ret;
	}

	public List<SqlLog> getAllThreadSqls() {
		List<SqlLog> ret = new ArrayList<>(512);
		for (Map.Entry<Thread, LinkedList<SqlLog>> entry : allLogs.entrySet()) {
			ret.addAll(entry.getValue());
		}
		return ret;
	}
}
