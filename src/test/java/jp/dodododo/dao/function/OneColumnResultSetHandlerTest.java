package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Id;
import jp.dodododo.dao.annotation.IdDefSet;
import jp.dodododo.dao.exception.UnsupportedTypeException;
import jp.dodododo.dao.id.Sequence;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class OneColumnResultSetHandlerTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Test
	public void testInsertAndSelect() {
		dao = newTestDao(dbTestRule.getDataSource());
		dao.setSqlLogRegistry(logRegistry);

		List<String> resultStringList = dao.select("SELECT ename FROM emp ORDER BY empno", String.class);
		assertEquals("SMITH", resultStringList.get(0));

		List<Integer> resultIntegerList = dao.select("SELECT sal FROM emp ORDER BY empno", Integer.class);
		assertEquals(800, (int) resultIntegerList.get(0));

		List<Integer> resultBigDecimalList = dao.select("SELECT sal FROM emp ORDER BY empno", Integer.class);
		assertEquals(new Integer("800"), resultBigDecimalList.get(0));

		@SuppressWarnings("rawtypes")
		List<Map> resultMapList = dao.select("SELECT * FROM emp ORDER BY empno", Map.class);
		assertEquals(7369, ((Number) resultMapList.get(0).get("empno")).intValue());
		assertEquals("SMITH", resultMapList.get(0).get("ename"));
		assertEquals(800, ((Number) resultMapList.get(0).get("sal")).intValue());

		try {
			dao.select("SELECT sal FROM emp ORDER BY empno", Class.class);
			fail();
		} catch (UnsupportedTypeException success) {
			System.out.println(success.getMessage());
		}
	}

	public static class Emp {
		public Empno no = new Empno();

		public Person person;

		public String JOB;

		public String MGR;

		public String HIREDATE;

		public String SAL;

		public String COMM;

		@Column(table = "detp", value = "tstamp")
		public Date TSTAMP;

		public Dept dept;
	}

	public static class Empno {
		@Id(value = @IdDefSet(type = Sequence.class, name = "sequence"), targetTables = { "emp" })
		public String EMPNO;
	}

	public static class Person {
		@Column("ename")
		public String NAME;

		@Column(table = "emp", value = "Tstamp")
		public Date TSTAMP;

	}

	public static class Dept {
		@Column("deptNO")
		public String DEPTNO;
	}
}
