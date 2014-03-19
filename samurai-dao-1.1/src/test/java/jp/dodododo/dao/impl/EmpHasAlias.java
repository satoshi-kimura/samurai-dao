package jp.dodododo.dao.impl;

import jp.dodododo.dao.annotation.Column;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EmpHasAlias {
	@Column(value = "a", alias = { "B", "EMPNO" })
	public String NO;

	public String ENAME;

	public String JOB;

	public String MGR;

	public String HIREDATE;

	public String SAL;

	public String COMM;

	public String DEPTNO;

	public String TSTAMP;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
