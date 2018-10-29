package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.NumKey;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class HasListOfEnumPropertyTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Test
	public void testInsertAndSelect() {
		dao = newTestDao(getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		List<Dept> deptList = dao
				.select("SELECT DEPT.DEPTNO as DEPTNO, EMPNO FROM DEPT, EMP where DEPT.DEPTNO = EMP.DEPTNO AND DEPT.DEPTNO = 10",
						Dept.class);

		assertEquals(1, deptList.size());
		assertEquals(3, deptList.get(0).empNo.size());
	}

	public static class Dept {
		public String DEPTNO;

		public String DNAME;

		public List<EmpNo> empNo;
	}

	public static enum EmpNo {
		Emp7782(7782), Emp7839(7839), Emp7934(7934);

		private int no;

		private EmpNo(int no) {
			this.no = no;
		}

		@NumKey
		public int getNo() {
			return no;
		}

	}

	private DataSource getDataSource() {
		return dbTestRule.getDataSource();
	}
}
