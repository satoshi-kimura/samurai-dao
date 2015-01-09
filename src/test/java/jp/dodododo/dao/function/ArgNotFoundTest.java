package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;
import jp.dodododo.dao.Dao;
import jp.dodododo.dao.exception.ArgNotFoundException;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;

public class ArgNotFoundTest extends S2TestCase {

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

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

	public void testSelect() {
		dao = newTestDao(getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		try {
			dao.selectMap("select * from emp where id = /*arg1*/0", args("arg0", "1", "arg2", "1"));
		} catch (ArgNotFoundException e) {
			assertEquals("引数が見つかりませんでした。[引数名=arg1, SQL=select * from emp where id = /*arg1*/0]", e.getMessage());
		}
	}
}
