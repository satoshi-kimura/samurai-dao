package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class HasListOfIntPropertyTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Test
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

	private DataSource getDataSource() {
		return dbTestRule.getDataSource();
	}
}
