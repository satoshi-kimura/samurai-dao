package jp.dodododo.dao.dialect;

public class MaxDB extends Standard {

	@Override
	public String getSuffix() {
		return "_maxdb";
	}

	@Override
	public String sequenceNextValSql(String sequenceName) {
		return "select " + sequenceName + ".nextval from dual";
	}
}
