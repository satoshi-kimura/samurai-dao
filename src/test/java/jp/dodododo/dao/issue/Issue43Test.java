package jp.dodododo.dao.issue;

import static jp.dodododo.dao.sql.GenericSql.*;
import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.util.List;
import java.util.Map;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Bean;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.row.Row;

import org.seasar.extension.unit.S2TestCase;

public class Issue43Test extends S2TestCase {

	private Dao dao;

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

	public void test() throws Exception {
		dao = newTestDao(getDataSource());

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
