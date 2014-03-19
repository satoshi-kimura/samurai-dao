package jp.dodododo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import jp.dodododo.dao.IterationCallback;

public class EmptyIterationCallback<ROW> implements IterationCallback<ROW> {

	@SuppressWarnings("rawtypes")
	private static final IterationCallback<?> instance = new EmptyIterationCallback();

	@SuppressWarnings("unchecked")
	public static <ROW> IterationCallback<ROW> getInstance() {
		return (IterationCallback<ROW>) instance;
	}

	public void iterate(ROW object) {
	}

	public List<ROW> getResult() {
		return new ArrayList<ROW>();
	}
}
