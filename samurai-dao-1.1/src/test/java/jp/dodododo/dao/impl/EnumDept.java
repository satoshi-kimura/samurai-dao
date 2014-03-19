package jp.dodododo.dao.impl;

@SuppressWarnings("deprecation")
public enum EnumDept implements jp.dodododo.dao.util.EnumConverter.NumKey {
	ACCOUNTING(10), RESEARCH(20), SALES(30), OPERATIONS(40);

	private int deptId;

	private EnumDept(int deptId) {
		this.deptId = deptId;
	}

	public double num() {
		return deptId;
	}
}
