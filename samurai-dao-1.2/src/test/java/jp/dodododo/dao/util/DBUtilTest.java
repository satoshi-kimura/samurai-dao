package jp.dodododo.dao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.dodododo.dao.metadata.TableMetaData;

import org.seasar.extension.unit.S2TestCase;

public class DBUtilTest extends S2TestCase {

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

	public void testGetTableNames() throws Exception {
		Map<String, String> tableNames = DBUtil.getTableNames(getConnection());
		assertTrue(0 < tableNames.size());
	}

	public void testGetViewNames() throws Exception {
		Map<String, String> viewNames = DBUtil.getViewNames(getConnection());
		List<String> viewList = new ArrayList<String>(viewNames.values());
		assertEquals(1, viewNames.size());
		assertEquals("EMP_VIEW", viewList.get(0));

		TableMetaData tableMetaData = new TableMetaData(getConnection(), "EMP_VIEW");
		List<String> columnNames = tableMetaData.getColumnNames();
		assertTrue(columnNames.contains("EMPNO"));
		assertTrue(columnNames.contains("ENAME"));
		assertTrue(columnNames.contains("JOB"));
		assertTrue(columnNames.contains("MGR"));
		assertTrue(columnNames.contains("HIREDATE"));
		assertTrue(columnNames.contains("SAL"));
		assertTrue(columnNames.contains("COMM"));
		assertTrue(columnNames.contains("DEPTNO"));
		assertTrue(columnNames.contains("TSTAMP"));
	}
}
