package jp.dodododo.dao.function;

import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.util.Date;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Id;
import jp.dodododo.dao.annotation.IdDefSet;
import jp.dodododo.dao.dialect.sqlite.SQLite;
import jp.dodododo.dao.id.Identity;
import jp.dodododo.dao.id.Sequence;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.types.TypeConverter;

import org.seasar.extension.unit.S2TestCase;

public class SomeObjectTest extends S2TestCase {

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
		Emp emp = new Emp();
		emp.dept = new Dept();
		emp.dept.DEPTNO = "10";
		emp.person = new Person();
		emp.COMM = "2";
		// emp.EMPNO = "1";
		emp.TSTAMP = null;
		emp.person.TSTAMP = new Date();
		emp.person.NAME = "ename";
		int count = dao.insert("emp", emp);
		assertEquals(1, count);
		String empNo = emp.no.EMPNO;

		List<Emp> select = dao.select("select * from emp where EMPNO =" + empNo, Emp.class);
		assertEquals(empNo, select.get(0).no.EMPNO);
		assertEquals(new Integer(2), TypeConverter.convert(select.get(0).COMM, Integer.class));
		assertEquals("10", select.get(0).dept.DEPTNO);
		assertEquals("ename", select.get(0).person.NAME);
		assertNotNull(select.get(0).person.TSTAMP);
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
		@Id(value = { @IdDefSet(type = Sequence.class, name = "sequence"), @IdDefSet(type = Identity.class, db = SQLite.class) }, targetTables = { "emp" })
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
