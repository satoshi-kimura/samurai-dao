package jp.dodododo.dao.issue;

import static jp.dodododo.dao.sql.GenericSql.*;
import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Bean;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.row.Row;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class Issue43Test {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	@Test
	public void test() throws Exception {
		dao = newTestDao(dbTestRule.getDataSource());

		List<Emp> empList = dao.select(ALL, Emp.class);
		for (Emp emp : empList) {
			assertNotNull(emp.empno);
			assertNotNull(emp.dept.deptno);
		}
	}

	public static class Emp {
		protected String empno;
		protected Dept dept;

		public Emp(@Column("empno") String empno, Dept dept) {
			this.empno = empno;
			this.dept = dept;
		}
	}

	@Bean(createMethod = "@jp.dodododo.dao.issue.Issue43Test$Dept@create(resultSetMap)")
	public static class Dept {

		private String deptno;

		private Dept(String deptno) {
			this.deptno = deptno;
		}

		public static Dept create(Map<String, Object> row) {
			return new Dept(new Row(row).getString("deptno"));
		}
	}
}
