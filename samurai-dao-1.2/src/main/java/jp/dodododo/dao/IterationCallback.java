package jp.dodododo.dao;

import java.util.List;

public interface IterationCallback<ROW> {
	void iterate(ROW row);

	List<ROW> getResult();
}
