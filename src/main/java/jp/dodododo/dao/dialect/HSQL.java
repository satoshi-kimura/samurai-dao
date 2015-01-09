package jp.dodododo.dao.dialect;

public class HSQL extends Standard {

	@Override
	public String getSuffix() {
		return "_hsql";
	}

	@Override
	public String identitySelectSql() {
		return "CALL IDENTITY()";
	}

	@Override
	public String sequenceNextValSql(String sequenceName) {
		return "SELECT NEXT VALUE FOR " + sequenceName
				+ " FROM INFORMATION_SCHEMA.SYSTEM_TABLES WHERE table_name = 'SYSTEM_TABLES'";
	}

}
