package jp.dodododo.dao.impl;

import static jp.dodododo.dao.sql.GenericSql.*;
import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Table;
import jp.dodododo.dao.log.SqlLogRegistry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.seasar.extension.unit.S2TestCase;

public class TimeTest extends S2TestCase {
	private Dao dao;

	private SqlLogRegistry logRegistry = new SqlLogRegistry();

	@Override
	public void setUp() throws Exception {
		include("jdbc.dicon");
	}

	@Override
	public void tearDown() throws Exception {
		logRegistry.clear();
	}

	@Override
	protected boolean needTransaction() {
		return true;
	}

	public void testSelect() {
		dao = newTestDao(getDataSource());

		List<TimeBean1> list1 = dao.select(ALL, TimeBean1.class);
		list1.stream().forEach(bean -> System.out.println(bean));

		List<TimeBean2> list2 = dao.select(ALL, TimeBean2.class);
		list2.stream().forEach(bean -> System.out.println(bean));
	}

	@Table("EMP")
	public static class TimeBean1 { // HIREDATE

		@Column("EMPNO")
		public int id;

		@Column("TSTAMP")
		public Date date;

		@Column("TSTAMP")
		public Timestamp timestamp;

		@Column("TSTAMP")
		public Instant instant;

		@Column("TSTAMP")
		public LocalDate localDate;

		@Column("TSTAMP")
		public LocalDateTime localDateTime;

		@Column("TSTAMP")
		public LocalTime localTime;

		@Column("TSTAMP")
		public Year year;

		@Column("TSTAMP")
		public YearMonth yearMonth;

		@Column("TSTAMP")
		public MonthDay monthDay;

		// TODO add @Zone Annotation
		//	@Column("TSTAMP")
		//	public OffsetDateTime offsetDateTime;

		//	@Column("TSTAMP")
		//	public OffsetTime offsetTime;

		//	@Column("TSTAMP")
		//	public ZonedDateTime zonedDateTime;

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
	@Table("EMP")
	public static class TimeBean2 { // HIREDATE

	}

}
