package jp.dodododo.dao.issue;

import static jp.dodododo.dao.sql.GenericSql.*;
import static jp.dodododo.dao.sql.Operator.*;
import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.script.Each;

import org.seasar.extension.unit.S2TestCase;

public class Issue46Test extends S2TestCase {

	private Dao dao;

	@Override
	public void setUp() throws Exception {
		include("jdbc.dicon");
	}

	@Override
	public void tearDown() throws Exception {
	}

	@Override
	protected boolean needTransaction() {
		return true;
	}

	public void test1() throws Exception {
		dao = newTestDao(getDataSource());

		final AtomicBoolean invoked = new AtomicBoolean(false);
		dao.selectMap("select * from emp", by("empno", 1), new Each() {
			@SuppressWarnings("unused")
			public void each(Map<String, Object> row) {
				invoked.getAndSet(true);
			}
		});
		assertTrue(invoked.get());
	}

	public void test2() throws Exception {
		dao = newTestDao(getDataSource());

		final AtomicBoolean invoked = new AtomicBoolean(false);
		dao.selectMap(ALL, from("emp"), new Each() {
			@SuppressWarnings("unused")
			public void each(Map<String, Object> row) {
				invoked.getAndSet(true);
			}
		});
		assertTrue(invoked.get());
	}

	public void test3() throws Exception {
		dao = newTestDao(getDataSource());

		final AtomicBoolean invoked = new AtomicBoolean(false);
		dao.selectMap("select * from emp", new Each() {
			@SuppressWarnings("unused")
			public void each(Map<String, Object> row) {
				invoked.getAndSet(true);
			}
		});
		assertTrue(invoked.get());
	}

	public void test4() throws Exception {
		dao = newTestDao(getDataSource());

		final AtomicBoolean invoked = new AtomicBoolean(false);
		dao.selectMap(from("emp"), where("empno", ge("100")), new Each() {
			@SuppressWarnings("unused")
			public void each(Map<String, Object> row) {
				invoked.getAndSet(true);
			}
		});
		assertTrue(invoked.get());
	}
}
