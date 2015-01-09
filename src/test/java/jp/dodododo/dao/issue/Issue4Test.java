package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Table;
import jp.dodododo.dao.exception.SQLRuntimeException;
import jp.dodododo.dao.impl.Dept;
import jp.dodododo.dao.impl.Emp;

import org.seasar.extension.unit.S2TestCase;

public class Issue4Test extends S2TestCase {

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
		RootEntity rootEntity = new RootEntity();
		rootEntity.emp.setCOMM("2");
		rootEntity.emp.setDEPTNO("10");
		rootEntity.emp.setEMPNO("1");
		rootEntity.emp.setENAME("ename");
		int count = dao.insert(rootEntity);
		assertEquals(1, count);

		try {
			dao.insert(rootEntity);
			fail();
		} catch (SQLRuntimeException success) {
		}
	}

	@Table("EMP")
	public static class RootEntity {
		public Dept a = new Dept();
		public Emp emp = new Emp();
	}
}
