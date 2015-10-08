package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static org.junit.Assert.*;
import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class Issue7Test {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	@Test
	public void test() {
		dao = newTestDao(dbTestRule.getDataSource());
		Emp emp = new Emp();
		int count = dao.insert(emp);
		assertEquals(1, count);
	}

	public static class Emp {
		public No no = No.TWO;
	}

	public static enum No {

		ONE(1), TWO(2);

		@Column("EMPNO")
		public int no;

		private No(int no) {
			this.no = no;
		}

	}
}
