package jp.dodododo.dao.handler.impl;

import java.util.HashMap;
import java.util.Map;

import jp.dodododo.dao.IterationCallback;

public class HashMapResultSetHandler extends jp.dodododo.dao.handler.impl.MapResultSetHandler {

	protected HashMapResultSetHandler() {
	}

	public HashMapResultSetHandler(IterationCallback<Map<String, Object>> callback) {
		super(callback);
	}

	public HashMapResultSetHandler(IterationCallback<Map<String, Object>> callback, Map<String, Object> arg) {
		super(callback, arg);
	}

	@Override
	protected Map<String, Object> newInstance() {
		Map<String, Object> ret = new HashMap<>();
		ret.putAll(arg);
		return ret;
	}
}
