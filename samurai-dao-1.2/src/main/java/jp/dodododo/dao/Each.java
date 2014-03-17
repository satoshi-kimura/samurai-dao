package jp.dodododo.dao;

import java.util.List;

import jp.dodododo.dao.IterationCallback;

public abstract class Each<T> implements IterationCallback<T> {

	@Override
	public final List<T> getResult() {
		return null;
	}

	public final void iterate(T x) {
		each(x);
	}

	/**
	 * @param x
	 */
	protected void each(T it) {
	}

}
