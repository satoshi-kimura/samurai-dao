package jp.dodododo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import jp.dodododo.dao.IterationCallback;

public class ListIterationCallback<ROW> implements IterationCallback<ROW> {
	protected List<ROW> list = new ArrayList<ROW>(1024);

    @Override
	public void iterate(ROW object) {
		list.add(object);
	}

    @Override
	public List<ROW> getResult() {
		return list;
	}
}
