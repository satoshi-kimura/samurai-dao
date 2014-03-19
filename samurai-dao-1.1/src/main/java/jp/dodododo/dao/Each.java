package jp.dodododo.dao;

import java.util.List;

public abstract class Each<T> implements IterationCallback<T> {

	@Override
	public final List<T> getResult() {
		return null;
	}

	public final void iterate(T x) {
		each(x);
	}

	/**
	 * @param it it
	 */
	protected void each(T it) {
	}

}
