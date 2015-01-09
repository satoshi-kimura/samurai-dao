package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;
import jp.dodododo.dao.Dao;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.types.TypeConverter;

import org.seasar.extension.unit.S2TestCase;

public class InsertObjectAndMap extends S2TestCase {

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

	public void testInsertAndSelect() {
		dao = newTestDao(getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		assertFalse(dao.exists(new Emp(1)));
		assertFalse(dao.exists(new Emp(2)));
		assertFalse(dao.exists("emp", new Emp(1), args("ename", "nnn", "job", "jjj")));
		int count = dao.insert("emp", new Emp(1), args("ename", "nnn", "job", "jjj"));
		assertEquals(1, count);
		assertFalse(dao.exists(new Emp(2)));
		assertTrue(dao.exists(new Emp(1)));
		assertTrue(dao.exists("emp", args("ename", "nnn", "job", "jjj"), new Emp(1)));
		count = dao.insert("emp", new Emp(2), args("ename", "nnn", "job", "jjj"));
		assertEquals(1, count);
		assertTrue(dao.exists(new Emp(1)));
		assertTrue(dao.exists(new Emp(2)));

		TypeConverter result = new TypeConverter(dao.selectOneMap("select * from emp where empno = 1"));
		assertEquals(1, result.getInteger("empno").intValue());
		assertEquals("nnn", result.getString("ename"));
		assertEquals("jjj", result.getString("job"));
	}

	public static class Emp {

		public String EMPNO = "1";

		public Emp(int empno) {
			EMPNO = Integer.toString(empno);
		}

	}

}
