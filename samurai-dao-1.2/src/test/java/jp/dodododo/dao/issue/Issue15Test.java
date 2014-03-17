package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;

import java.text.ParseException;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.exception.DaoRuntimeException;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;


public class Issue15Test extends S2TestCase {

	private Dao dao;
	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Override
	public void setUp() throws Exception {
		include("jdbc.dicon");
		DaoConfig.getDefaultConfig().setFormats("yyyy/MM/dd", "yyyy-MM-dd");
	}

	@Override
	public void tearDown() throws Exception {
	}

	@Override
	protected boolean needTransaction() {
		return true;
	}

	public void test() throws ParseException {
		dao = newTestDao(getDataSource());
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
