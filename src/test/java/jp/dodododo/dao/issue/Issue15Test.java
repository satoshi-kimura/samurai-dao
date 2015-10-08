package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;
import static org.junit.Assert.*;

import java.text.ParseException;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.exception.DaoRuntimeException;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class Issue15Test {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;
	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Before
	public void setUp() throws Exception {
		DaoConfig.getDefaultConfig().setFormats("yyyy/MM/dd", "yyyy-MM-dd");
	}

	@Test
	public void test() throws ParseException {
		dao = newTestDao(dbTestRule.getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		try {
			dao.update("EMP", map("ename", "mike"));
			fail();
		} catch (DaoRuntimeException success) {
			assertEquals("00044", success.getMessageCode());
		}

		try {
			dao.delete("EMP", map("ename", "mike"));
			fail();
		} catch (DaoRuntimeException success) {
			assertEquals("00044", success.getMessageCode());
		}
	}
}
