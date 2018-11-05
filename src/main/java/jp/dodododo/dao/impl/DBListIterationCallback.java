package jp.dodododo.dao.impl;

import java.util.List;

import jp.dodododo.dao.IterationCallback;
import jp.dodododo.dao.util.DbArrayList;

public class DBListIterationCallback<ROW> implements IterationCallback<ROW> {
	protected List<ROW> list = new DbArrayList<>();

    @Override
	public void iterate(ROW object) {
		list.add(object);
	}

    @Override
	public List<ROW> getResult() {
		return list;
	}
}
