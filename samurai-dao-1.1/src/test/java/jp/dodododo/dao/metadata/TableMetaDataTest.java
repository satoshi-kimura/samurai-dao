package jp.dodododo.dao.metadata;

import java.util.List;

import org.seasar.extension.unit.S2TestCase;

public class TableMetaDataTest extends S2TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		include("jdbc.dicon");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	protected boolean needTransaction() {
		return true;
	}

	public void testTableMetaDataConnectionString() {
		TableMetaData data = new TableMetaData(getConnection(), "EMP");

		ColumnMetaData columnMetaData = data.getColumnMetaData("ename");
		assertNotNull(columnMetaData);
		ColumnMetaData pk = data.getColumnMetaData("EMPNO");
		assertTrue(pk.isPrimaryKey());

		List<ForeignKey> importedKeys = data.getImportedKeys();
		assertEquals(1, importedKeys.size());
		ForeignKey foreignKey = importedKeys.get(0);
		ForeignKeyColumn column = foreignKey.get(0);
		assertEquals("EMP", column.getTableName());
		assertEquals("DEPTNO", column.getColumnName());
		assertEquals("DEPT", column.getReference().getTable());
		assertEquals("DEPTNO", column.getReference().getColumn());

	}

	public void testTableMetaDataDataSourceString() {
		TableMetaData data = new TableMetaData(getDataSource(), "EMP");

		ColumnMetaData columnMetaData = data.getColumnMetaData("ename");
		assertNotNull(columnMetaData);
		ColumnMetaData pk = data.getColumnMetaData("EMPNO");
		assertTrue(pk.isPrimaryKey());
	}

}
