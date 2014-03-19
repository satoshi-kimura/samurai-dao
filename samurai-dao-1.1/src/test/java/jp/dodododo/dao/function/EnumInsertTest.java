package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.util.Date;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.NumKey;
import jp.dodododo.dao.exception.SQLRuntimeException;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;

public class EnumInsertTest extends S2TestCase {

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

		int count = dao.insert("emp", Emp.EMP);
		assertEquals(1, count);

		try {
			count = dao.insert("emp", Emp.EMP);
			fail();
		} catch (SQLRuntimeException success) {
		}

		List<Emp> result = dao.select("SELECT empno FROM emp where empno=1", Emp.class);
		assertEquals(result.get(0).getClass(), Emp.class);
		assertEquals(result.get(0).getId(), Emp.EMP.getId());
	}

	public static enum Emp {
		EMP;

		public String EMPNO = "1";

		public String NAME;

		public Date TSTAMP;

		public String JOB;

		public String MGR;

		public String HIREDATE;

		public String SAL;

		public String COMM;

		public String DEPTNO;

		public char test;

		@NumKey
		public int getId() {
			return Integer.parseInt(EMPNO);
		}
	}
}
