package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Arg;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Id;
import jp.dodododo.dao.annotation.IdDefSet;
import jp.dodododo.dao.dialect.MySQL;
import jp.dodododo.dao.dialect.sqlite.SQLite;
import jp.dodododo.dao.id.Identity;
import jp.dodododo.dao.id.Sequence;
import jp.dodododo.dao.impl.Dept;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class ArgAnnotationTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Test
	public void testInsertAndSelect() {
		dao = newTestDao(dbTestRule.getDataSource());

		dao.setSqlLogRegistry(logRegistry);
		Emp emp = new Emp();
		emp.dept = new Dept();
		emp.dept.setDEPTNO("10");
		emp.dept.setDNAME("dept__name");
		emp.COMM = "2";
		// emp.EMPNO = "1";
		emp.TSTAMP = null;
		emp.TSTAMP = new Date();
		emp.NAME = "ename";
		dao.insert("emp", emp);
		// dao.insert(emp.dept);
		String empNo = emp.EMPNO;

		List<Emp> select = dao.select("select dept.deptno as DEPTNO from emp, dept where emp.deptno = dept.deptno and empno = " + empNo,
				args("dept", new Dept2("123"), "job", "argJob", "MGR", new StringBuilder("argMGR"), "null", null, "dept4", ""), Emp.class);

		emp = select.get(0);
		assertTrue(emp.dept instanceof Dept2);
		assertEquals("123", emp.dept.getDEPTNO());
		assertEquals("10", emp.dept2.getDEPTNO());
		assertTrue(emp.dept3 instanceof Dept2);
		assertEquals("123", emp.dept3.getDEPTNO());
		assertNull(emp.dept4);
		assertEquals("argJob", emp.JOB);
		assertEquals("argMGR", emp.MGR);
	}

	public static class Emp {
		@Id(value = { @IdDefSet(type = Sequence.class, name = "sequence"),
				@IdDefSet(type = Identity.class, db = SQLite.class),
				@IdDefSet(type = Identity.class, db = MySQL.class) },
				targetTables = { "emp" })
		public String EMPNO;

		@Column("ename")
		public String NAME;

		@Column(table = "emp", value = "Tstamp")
		public Date TSTAMP;

		public String JOB;

		public String MGR;

		public String HIREDATE;

		public String SAL;

		public String COMM;

		private Dept dept;

		private Dept dept2;
		private Dept dept3;
		private Dept dept4;

		public Emp() {
		}

		public Emp(@Arg("dept") Dept dept, Dept dept2, @Arg("dept") Dept dept3, @Arg("dept4") Dept dept4, @Arg("job") String JOB, @Arg("mgr") String MGR,
				@Arg("null") int test, @Arg("null") long test2, @Arg("null") boolean test3) {
			this.dept = dept;
			this.dept2 = dept2;
			this.dept3 = dept3;
			this.dept4 = dept4;
			this.JOB = JOB;
			this.MGR = MGR;
		}

		public Dept getDept() {
			return dept;
		}

		public Dept getDept2() {
			return dept2;
		}

		public Dept getDept3() {
			return dept2;
		}
	}

	public static class Dept2 extends Dept {

		private String DEPTNO;

		public Dept2(String DEPTNO) {
			this.DEPTNO = DEPTNO;
		}

		@Override
		public String getDEPTNO() {
			return DEPTNO;
		}

	}
}
