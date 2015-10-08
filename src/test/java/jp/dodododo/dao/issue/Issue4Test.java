package jp.dodododo.dao.issue;

import static jp.dodododo.dao.unit.UnitTestUtil.*;
import static org.junit.Assert.*;
import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Table;
import jp.dodododo.dao.exception.SQLRuntimeException;
import jp.dodododo.dao.impl.Dept;
import jp.dodododo.dao.impl.Emp;
import jp.dodododo.dao.unit.DbTestRule;

import org.junit.Rule;
import org.junit.Test;

public class Issue4Test {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	private Dao dao;

	@Test
	public void test() {
		dao = newTestDao(dbTestRule.getDataSource());
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
