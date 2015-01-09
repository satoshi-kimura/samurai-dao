package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.text.ParseException;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Bean;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;

public class Issue35Test extends S2TestCase {

	private Dao dao;
	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Override
	public void setUp() throws Exception {
		include("jdbc.dicon");
		DaoConfig.getDefaultConfig().setFormats("yyyy/MM/dd", "yyyy-MM-dd");
	}

	@Override
	public void tearDown() throws Exception {
	}

	@Override
	protected boolean needTransaction() {
		return true;
	}

	public void test() throws ParseException {
		dao = newTestDao(getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		Emp emp = dao.selectOne("select * from emp where empno = 7369", Emp.class);
		assertNotNull(emp.dept);
		assertNotNull(emp.dept2);
		assertNotNull(emp.EMPNO);
		assertNotNull(emp.ENAME);

		emp = dao.selectOne("select empno, ename, 0 as DEPTNO from emp where empno = 7369", Emp.class);
		assertNotNull(emp.dept);
		assertNotNull(emp.dept2);

		emp = dao.selectOne("select empno, ename, null as DEPTNO from emp where empno = 7369", Emp.class);
		assertNull(emp.dept);
		assertNotNull(emp.EMPNO);
		assertNotNull(emp.ENAME);

		assertNotNull(emp.dept2);
		assertEquals(0, emp.dept2.DEPTNO);
		assertNull(emp.dept2.DNAME);

	}

	public static class Emp {
		public String EMPNO;

		public String ENAME;
		protected Dept dept;
		protected Dept dept2;
		protected Dept dept3;
		protected Dept dept4;

		public Emp(@Bean(nullable = true) Dept dept, Dept dept2, @Bean(nullable = true) Dept dept3, Dept dept4) {
			this.dept = dept;
			this.dept2 = dept2;
			this.dept3 = dept3;
			this.dept4 = dept4;
		}
	}

	public static class Dept {
		protected int DEPTNO = -1;

		protected String DNAME = "-";

		public Dept(@Column("DEPTNO") int DEPTNO, @Column("DNAME") String DNAME) {
			this.DEPTNO = DEPTNO;
			this.DNAME = DNAME;
		}
	}

	public static class Dept2 {
		protected Integer DEPTNO = -1;

		protected String DNAME = "-";

		public Dept2(@Column("DEPTNO") Integer DEPTNO, @Column("DNAME") String DNAME) {
			this.DEPTNO = DEPTNO;
			this.DNAME = DNAME;
		}
	}

	public void testPrimitiveArgs() {
		Dao dao = newTestDao(getConnection());
		List<PrimitiveArgsEmp> list = dao.select("select * from emp where comm > 0", PrimitiveArgsEmp.class);
		assertTrue(list.isEmpty() == false);
		for (PrimitiveArgsEmp emp : list) {
			assertTrue(0 < emp.comm);
		}
		list = dao.select("select * from emp where comm is null", PrimitiveArgsEmp.class);
		assertTrue(list.isEmpty() == false);
		for (PrimitiveArgsEmp emp : list) {
			assertTrue(0 == emp.comm);
		}
		List<ObjectArgsEmp> list2 = dao.select("select * from emp where comm > 0", ObjectArgsEmp.class);
		assertTrue(list.isEmpty() == false);
		for (ObjectArgsEmp emp : list2) {
			assertTrue(0 < emp.comm);
		}
		list2 = dao.select("select * from emp where comm is null", ObjectArgsEmp.class);
		assertTrue(list.isEmpty() == false);
		for (ObjectArgsEmp emp : list2) {
			assertNull(emp.comm);
		}
	}

	public static class PrimitiveArgsEmp {
		protected int empNo;
		protected String name;
		protected int comm;

		public PrimitiveArgsEmp(@Column("EMPNO") int empNo, @Column("ENAME") String name, @Column("COMM") int comm) {
			this.empNo = empNo;
			this.name = name;
			this.comm = comm;
		}
	}

	public static class ObjectArgsEmp {
		protected int empNo;
		protected String name;
		protected Integer comm;

		public ObjectArgsEmp(@Column("EMPNO") int empNo, @Column("ENAME") String name, @Column("COMM") Integer comm) {
			this.empNo = empNo;
			this.name = name;
			this.comm = comm;
		}
	}
}
