package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;
import static org.junit.Assert.*;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.exception.ArgNotFoundException;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class ArgNotFoundTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();


	@Test
	public void testSelect() {
		dao = newTestDao(dbTestRule.getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		try {
			dao.selectMap("select * from emp where id = /*arg1*/0", args("arg0", "1", "arg2", "1"));
		} catch (ArgNotFoundException e) {
			assertEquals("引数が見つかりませんでした。[引数名=arg1, SQL=select * from emp where id = /*arg1*/0]", e.getMessage());
		}
	}
}
