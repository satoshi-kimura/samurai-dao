package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static jp.dodododo.dao.util.DaoUtil.*;
import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Arg;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Table;

import org.seasar.extension.unit.S2TestCase;

public class Issue49Test extends S2TestCase {

	private Dao dao;

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

	public void test() throws Exception {
		dao = newTestDao(getDataSource());

		Emp1 emp1 = dao.selectOne(Emp1.class, from("emp"), by("empno", 7369, "argname", "a"));
		assertEquals(7369, emp1.empno);
		assertEquals("SMITH", emp1.name);

		Emp2 emp2 = dao.selectOne(Emp2.class, from("emp"), by("empno", 7369, "argname", "a"));
		assertEquals(7369, emp2.empno);
		assertEquals("a", emp2.name);

		Emp2 emp3 = dao.selectOne(Emp2.class, from("emp"), by("empno", 7369, "foo", "a"));
		assertEquals(7369, emp3.empno);
		assertEquals("SMITH", emp3.name);

		Emp3 emp4 = dao.selectOne(Emp3.class, from("emp"), by("empno", 7369, "argname", "a"));
		assertEquals(7369, emp4.empno);
		assertEquals("a", emp4.name);

	}

	@Table("emp")
	public static class Emp1 {
		protected int empno;
		protected String name;

		public Emp1(@Column("empno") int empno, @Column("ename") @Arg("argname") String name) {
			this.empno = empno;
			this.name = name;
		}
	}

	@Table("emp")
	public static class Emp2 {
		protected int empno;
		protected String name;

		public Emp2(@Column("empno") int empno, @Arg("argname") @Column("ename") String name) {
			this.empno = empno;
			this.name = name;
		}
	}
	@Table("emp")
	public static class Emp3 {
		protected int empno;
		protected String name;

		public Emp3(@Column("empno") int empno, @Column("foo") @Arg("argname") String name) {
			this.empno = empno;
			this.name = name;
		}
	}

}
