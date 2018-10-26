package jp.dodododo.dao.function;

import static jp.dodododo.dao.sql.GenericSql.*;
import static jp.dodododo.dao.unit.Assert.*;
import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Id;
import jp.dodododo.dao.annotation.IdDefSet;
import jp.dodododo.dao.dialect.MySQL;
import jp.dodododo.dao.dialect.sqlite.SQLite;
import jp.dodododo.dao.id.Identity;
import jp.dodododo.dao.id.Sequence;
import jp.dodododo.dao.log.SqlLogRegistry;
import jp.dodododo.dao.row.Row;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class SelectRowTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Test
	public void testInsertAndSelect() {
		dao = newTestDao(dbTestRule.getDataSource());
		dao.setSqlLogRegistry(logRegistry);
		Emp emp = new Emp();
		emp.COMM = "2";
		// emp.EMPNO = "1";
		emp.TSTAMP = null;
		emp.TSTAMP = new Date();
		emp.NAME = "ename";
		int count = dao.insert("EMP", emp);
		assertEquals(1, count);
		Integer empNo = Integer.parseInt(emp.EMPNO);

		List<Row> select = dao.select("SELECT * FROM EMP WHERE empno = " + empNo, Row.class);
		assertEqualsIgnoreCase("SELECT * FROM emp WHERE empno = "+ empNo, logRegistry.getLast().getCompleteSql());
		assertEquals(empNo, select.get(0).getInteger("EMPNO"));
		assertEquals("" + empNo, select.get(0).getString("EMPNO"));
		assertEquals(new Integer(2), select.get(0).getInteger("COMM"));
		assertEquals("ename", select.get(0).getString("ENAME"));
		assertNotNull(select.get(0).getString("TSTAMP"));

		select = dao.select(SIMPLE_WHERE, args(TABLE_NAME, "EMP", "EMPNO", empNo), Row.class);
		assertEqualsIgnoreCase("SELECT * FROM EMP WHERE EMPNO = "+ empNo, logRegistry.getLast().getCompleteSql());
		assertEquals(empNo, select.get(0).getInteger("EMPNO"));
		assertEquals("" + empNo, select.get(0).getString("EMPNO"));
		assertEquals(new Integer(2), select.get(0).getInteger("COMM"));
		assertEquals("ename", select.get(0).getString("ENAME"));
		assertNotNull(select.get(0).getString("TSTAMP"));
	}

	public static class Emp {
		@Id(value = { @IdDefSet(type = Sequence.class, name = "sequence"), @IdDefSet(type = Identity.class, db = SQLite.class),
				@IdDefSet(type = Identity.class, db = MySQL.class) }, targetTables = { "EMP" })
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
	}

}
