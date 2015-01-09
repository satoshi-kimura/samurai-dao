package jp.dodododo.dao.impl;

import static jp.dodododo.dao.commons.Bool.*;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Property;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EmpConstructorHasDept {
	private String EMPNO;

	@Property(writable = TRUE)
	private String ENAME;

	@Property(writable = TRUE)
	private String JOB;

	@Property(writable = TRUE)
	private String MGR;

	@Property(writable = TRUE)
	private String HIREDATE;

	@Property(writable = TRUE)
	private String SAL;

	@Property(writable = TRUE)
	private String COMM;

	@Property(writable = TRUE)
	private String DEPTNO;

	@Property(writable = TRUE)
	private String TSTAMP;

	private Dept dept;

	private Dept dept2;

	public EmpConstructorHasDept(@Column("EMPNO") String EMPNO, Dept dept, Dept dept2) {
		this.EMPNO = EMPNO;
		this.dept = dept;
		this.dept2 = dept2;
	}

	public EmpConstructorHasDept(int EMPNO, String ENAME) {
	}

	public String getCOMM() {
		return COMM;
	}

	public String getDEPTNO() {
		return DEPTNO;
	}

	public String getEMPNO() {
		return EMPNO;
	}

	public String getENAME() {
		return ENAME;
	}

	public String getHIREDATE() {
		return HIREDATE;
	}

	public String getJOB() {
		return JOB;
	}

	public String getMGR() {
		return MGR;
	}

	public String getSAL() {
		return SAL;
	}

	public String getTSTAMP() {
		return TSTAMP;
	}

	public Dept getDept() {
		return dept;
	}

	public Dept getDept2() {
		return dept2;
	}

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
