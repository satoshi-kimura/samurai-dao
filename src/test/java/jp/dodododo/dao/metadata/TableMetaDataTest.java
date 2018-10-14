package jp.dodododo.dao.metadata;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import jp.dodododo.dao.unit.DbTestRule;
import jp.dodododo.dao.util.StringUtil;

import org.junit.Rule;
import org.junit.Test;

public class TableMetaDataTest {

	@Rule
	public DbTestRule dbTestRule = new DbTestRule();

	@Test
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
		assertTrue(column.getTableName(), StringUtil.equalsIgnoreCase("EMP", column.getTableName()));
		assertEquals("DEPTNO", column.getColumnName());
		assertTrue(column.getReference().getTable(), StringUtil.equalsIgnoreCase("DEPT", column.getReference().getTable()));
		assertEquals("DEPTNO", column.getReference().getColumn());
	}

	@Test
	public void testTableMetaDataDataSourceString() {
		TableMetaData data = new TableMetaData(getDataSource(), "EMP");

		ColumnMetaData columnMetaData = data.getColumnMetaData("ename");
		assertNotNull(columnMetaData);
		ColumnMetaData pk = data.getColumnMetaData("EMPNO");
		assertTrue(pk.isPrimaryKey());
	}

	private DataSource getDataSource() {
		return dbTestRule.getDataSource();
	}

	private Connection getConnection() {
		return dbTestRule.getConnection();
	}

}
