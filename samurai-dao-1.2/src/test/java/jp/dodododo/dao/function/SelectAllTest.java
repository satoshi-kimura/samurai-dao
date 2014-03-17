package jp.dodododo.dao.function;

import static jp.dodododo.dao.sql.GenericSql.*;
import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Bean;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Id;
import jp.dodododo.dao.annotation.IdDefSet;
import jp.dodododo.dao.columns.ResultSetColumn;
import jp.dodododo.dao.dialect.sqlite.SQLite;
import jp.dodododo.dao.id.Identity;
import jp.dodododo.dao.id.Sequence;
import jp.dodododo.dao.impl.Dept;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.seasar.extension.unit.S2TestCase;

public class SelectAllTest extends S2TestCase {

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

	public void testSelectAll() {
		dao = newTestDao(getDataSource());

		dao.setSqlLogRegistry(logRegistry);
		List<Emp> allEmp = dao.select(ALL, Emp.class);
		assertEquals(14, allEmp.size());
		Emp emp = allEmp.get(0);
		assertEquals("7369", emp.EMPNO);
		assertEquals("SMITH", emp.NAME);
		assertEquals("CLERK", emp.JOB);
		assertEquals("20", emp.dept.getDEPTNO());
		assertNull(emp.dept.getDNAME());
	}

	public static class Emp {
		@Id(value = { @IdDefSet(type = Sequence.class, name = "sequence"), @IdDefSet(type = Identity.class, db = SQLite.class) }, targetTables = { "emp" })
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

		public Emp(@Bean(createMethod = "@jp.dodododo.dao.function.CreateMethodConstructorTest@createDept(resultSet, columnList)") Dept dept) {
			this.dept = dept;
		}

		public Dept getDept() {
			return dept;
		}
	}

	public static Dept createDept(ResultSet rs, List<ResultSetColumn> columnList) throws SQLException {
		String deptno = rs.getString("DEPTNO");
		return new Dept2(deptno);
	}

	public static class Dept2 extends Dept {

		public String DEPTNO;

		public Dept2(String DEPTNO) {
			this.DEPTNO = DEPTNO;
		}

		@Override
		public String getDEPTNO() {
			return super.getDEPTNO();
		}

	}
}
