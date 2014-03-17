package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;

public class HasListOfIntPropertyTest extends S2TestCase {

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

		List<Dept> deptList = dao.select("SELECT dept.DEPTNO as DEPTNO, DNAME, EMPNO FROM dept, emp where dept.DEPTNO = emp.DEPTNO AND dept.DEPTNO = 10", Dept.class);

		assertEquals(1, deptList.size());
		assertEquals(3, deptList.get(0).empNo.size());
	}

	public static class Dept {
		public String DEPTNO;

		public String DNAME;

		public List<Integer> empNo;
	}
}
