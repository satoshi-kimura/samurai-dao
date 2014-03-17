package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;

import org.seasar.extension.unit.S2TestCase;

public class Issue7Test extends S2TestCase {

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

	public void test() {
		dao = newTestDao(getDataSource());
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
