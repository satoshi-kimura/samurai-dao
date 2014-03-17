package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.config.DaoConfig;
import jp.dodododo.dao.row.Row;
import jp.dodododo.dao.types.TypeConverter;

import org.seasar.extension.unit.S2TestCase;

public class Issue10Test extends S2TestCase {

	private Dao dao;

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
		EMP emp = new EMP();
		emp.EMPNO = "1";
		emp.HIREDATE = "2000-02-02";
		emp.TSTAMP = "2000/01/01";

		int count = dao.insert(emp);
		assertEquals(1, count);

		Row row= dao.selectOne("SELECT * FROM EMP WHERE EMPNO = 1", Row.class);
		assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20000202"), TypeConverter.convert(row.getObject("HIREDATE"), Date.class));
		assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20000101"), TypeConverter.convert(row.getObject("TSTAMP"), Date.class));
	}

	public static class EMP {
		public String EMPNO;
		public String ENAME;
		public String JOB;
		public String MGR;
		public String HIREDATE;
		public String SAL;
		public String COMM;
		public String DEPTNO;
		public String TSTAMP;
	}
}
