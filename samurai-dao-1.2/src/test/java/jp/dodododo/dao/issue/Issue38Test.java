package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.config.DaoConfig;

import org.seasar.extension.unit.S2TestCase;

public class Issue38Test extends S2TestCase {

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

		List<Dept> deptList = dao.select(
				"select dept.deptno, dept.dname, emp.empno, emp.ename from dept , emp where emp.deptno = dept.deptno order by dept.deptno", Dept.class);

		assertEquals(3, deptList.size());
		assertEquals(3, deptList.get(0).empList.size());
		assertEquals(5, deptList.get(1).empList.size());
		assertEquals(6, deptList.get(2).empList.size());

	}

	public static class Emp {
		@Column(value = "no", alias = "empno")
		private String no;
		@Column(value = "name", alias = "ename")
		private String name;

		public Emp(@Column(value = "no", alias = "empno") String no, @Column(value = "name", alias = "ename") String name) {
			this.no = no;
			this.name = name;
		}

		public String getNo() {
			return no;
		}

		public String getName() {
			return name;
		}

	}

	public static class Dept {
		@Column(value = "no", alias = "deptno")
		private String no;
		@Column(value = "name", alias = "dname")
		private String name;

		private List<Emp> empList = new ArrayList<Emp>();

		public Dept(@Column(value = "no", alias = "deptno") String no, @Column(value = "name", alias = "dname") String name) {
			this.no = no;
			this.name = name;

		}

		public List<Emp> getEmpList() {
			return empList;
		}

		public void setEmpList(List<Emp> empList) {
			this.empList = empList;
		}
	}
}
