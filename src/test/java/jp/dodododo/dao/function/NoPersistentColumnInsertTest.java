package jp.dodododo.dao.function;

import static jp.dodododo.dao.columns.ColumnsUtil.*;

import java.util.Date;

import jp.dodododo.dao.exception.SQLRuntimeException;
import jp.dodododo.dao.impl.RdbDao;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;

public class NoPersistentColumnInsertTest extends S2TestCase {

	private RdbDao dao;

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
		dao = new RdbDao(getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		int count = dao.insert("emp", Emp.EMP, npc("ename", "COMM", "deptNo", "HIREDATE", "MGR", "SAL", "TSTAMP", "JOB"));
		assertEquals(1, count);
		assertEquals("INSERT INTO emp ( EMPNO ) VALUES ( 1 )", logRegistry.getLast().getCompleteSql());

		try {
			count = dao.insert("emp", Emp.EMP);
			fail();
		} catch (SQLRuntimeException success) {
		}
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

	}
}
