package jp.dodododo.dao.unit;

import org.seasar.extension.unit.S2TestCase;

public class SqlFileExecuterTest extends S2TestCase {
	@Override
	public void setUp() throws Exception {
		include("jdbc.dicon");
	}

	public void testExecuteSelect() {
		SqlFileExecuter executer = new SqlFileExecuter(getConnection());
		String sqlFilePath = "jp/dodododo/dao/impl/many_to_one.sql";
		executer.executeSelect(sqlFilePath);

		sqlFilePath = "jp/dodododo/dao/impl/nest_if3.sql";
		executer.executeSelect(sqlFilePath);
	}

	public void testExecuteUpdate() {
		SqlFileExecuter executer = new SqlFileExecuter(getConnection());
		String sqlFilePath = "jp/dodododo/dao/unit/insert.sql";
		executer.executeUpdate(sqlFilePath);
	}

	@Override
	protected boolean needTransaction() {
		return true;
	}
}
