package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;

import java.util.Date;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Bean;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Id;
import jp.dodododo.dao.annotation.IdDefSet;
import jp.dodododo.dao.dialect.sqlite.SQLite;
import jp.dodododo.dao.id.Identity;
import jp.dodododo.dao.id.Sequence;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;

public class RelationNullTest extends S2TestCase {

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

	public void testSelect() {
		dao = newTestDao(getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		Emp emp = new Emp(new Dept(null));
		dao.insert(emp);

		emp = dao.selectOne("select * from emp where empno = /*empno*/0", args("empno", emp.EMPNO), Emp.class);
		assertNull(emp.getDept());
	}

	public static class Emp {
		@Id(value = { @IdDefSet(type = Sequence.class, name = "sequence"), @IdDefSet(type = Identity.class, db = SQLite.class) }, targetTables = { "emp" })
		public String EMPNO;

		public String ename;

		public Date Tstamp;

		public String JOB;

		public String MGR;

		public String HIREDATE;

		public String SAL;

		public String COMM;

		@Bean(nullable = true)
		public Dept dept;

		public Emp(@Bean(nullable = true) Dept dept) {
			this.dept = dept;
		}

		public Dept getDept() {
			return dept;
		}

		public void setDept(Dept dept) {
			this.dept = dept;
		}

	}

	public static class Dept {
		public String deptno;

		public Date tstamp;

		public Dept(@Column("DEPTNO") String deptno) {
			this.deptno = deptno;
		}
	}
}
