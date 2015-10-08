package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Bean;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Id;
import jp.dodododo.dao.annotation.IdDefSet;
import jp.dodododo.dao.id.Sequence;
import jp.dodododo.dao.impl.Dept;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class CreateMethodTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Test
	public void testMethodCreateBean() {
		dao = newTestDao(getDataSource());

		dao.setSqlLogRegistry(logRegistry);

		List<Emp> select = dao.select("select EMPNO, dept.DEPTNO as DEPTNO from emp, dept where emp.deptno = dept.deptno ",
				Emp.class);

		for (Emp selectedEmp : select) {

			if (selectedEmp.EMPNO.equals("23")) {
				assertTrue(selectedEmp.getClass().toString(), selectedEmp instanceof Smith);
			} else {
				assertFalse(selectedEmp.getClass().toString(), selectedEmp instanceof Smith);
				assertFalse(selectedEmp.getClass().toString(), selectedEmp instanceof Emp3);
				assertFalse(selectedEmp.getClass().toString(), selectedEmp instanceof Emp2);
			}

			assertNotNull(selectedEmp.dept);
		}

	}

	@Test
	public void testMethodCreateConstructor() {
		dao = newTestDao(getDataSource());

		dao.setSqlLogRegistry(logRegistry);

		List<Emp2> select = dao.select("select EMPNO, dept.deptno as deptno from emp, dept where emp.deptno = dept.deptno ",
				Emp2.class);

		for (Emp selectedEmp : select) {

			if (selectedEmp.EMPNO.equals("23")) {
				assertTrue(selectedEmp.getClass().toString(), selectedEmp instanceof Smith);
			} else {
				assertFalse(selectedEmp.getClass().toString(), selectedEmp instanceof Smith);
				assertFalse(selectedEmp.getClass().toString(), selectedEmp instanceof Emp3);
				assertTrue(selectedEmp.getClass().toString(), selectedEmp instanceof Emp2);
			}

			assertNotNull(selectedEmp.dept);
		}

	}

	@Test
	public void testMethodCreateClass() {
		dao = newTestDao(getDataSource());

		dao.setSqlLogRegistry(logRegistry);

		List<Emp3> select = dao.select("select EMPNO, dept.deptno as deptno from emp, dept where emp.deptno = dept.deptno ",
				Emp3.class);

		for (Emp selectedEmp : select) {

			if (selectedEmp.EMPNO.equals("23")) {
				assertTrue(selectedEmp.getClass().toString(), selectedEmp instanceof Smith);
			} else {
				assertFalse(selectedEmp.getClass().toString(), selectedEmp instanceof Smith);
				assertTrue(selectedEmp.getClass().toString(), selectedEmp instanceof Emp3);
				assertTrue(selectedEmp.getClass().toString(), selectedEmp instanceof Emp2);
			}

			assertNotNull(selectedEmp.dept);
		}
	}

	@Bean(createMethod = "@jp.dodododo.dao.function.CreateMethodTest@getConstructorForDao(resultSetMap)")
	public static class Emp2 extends Emp {
	}

	@Bean(createMethod = "@jp.dodododo.dao.function.CreateMethodTest@getBeanClassForDao(resultSetMap)")
	public static class Emp3 extends Emp2 {
	}

	@Bean(createMethod = "@jp.dodododo.dao.function.CreateMethodTest@createEmp(resultSetMap)")
	public static class Emp {
		@Id(value = @IdDefSet(type = Sequence.class, name = "sequence"), targetTables = { "emp" })
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

		public Emp() {
		}

		public Dept getDept() {
			return dept;
		}

		public void setDept(Dept dept) {
			this.dept = dept;
		}
	}

	public static Emp createEmp(Map<String, Object> rsMap) {
		int empno = ((Number) rsMap.get("empno")).intValue();
		if (empno == 23) {
			return new Smith();
		}
		return new Emp();
	}

	public static Class<? extends Emp> getBeanClassForDao(Map<String, Object> rsMap) throws Exception {
		int empno = ((Number) rsMap.get("empno")).intValue();
		if (empno == 23) {
			return Smith.class;
		}
		return Emp3.class;
	}

	public static Constructor<? extends Emp> getConstructorForDao(Map<String, Object> rsMap) throws Exception {
		int empno = ((Number) rsMap.get("empno")).intValue();
		if (empno == 23) {
			return Smith.class.getConstructor();
		}
		return Emp2.class.getConstructor();
	}

	public static class Smith extends Emp3 {
	}

	private DataSource getDataSource() {
		return dbTestRule.getDataSource();
	}
}
