package jp.dodododo.dao.impl;

import static jp.dodododo.dao.sql.GenericSql.*;
import static jp.dodododo.dao.unit.UnitTestUtil.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import jp.dodododo.dao.Dao;
import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.annotation.Table;
import jp.dodododo.dao.annotation.Zone;
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
		// TODO assert

		List<TimeBean2> list2 = dao.select(ALL, TimeBean2.class);
		list2.stream().forEach(bean -> System.out.println(bean));
		// TODO assert
	}

	@Table("EMP")
	public static class TimeBean1 {

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

		@Zone(id = "EST")
		@Column("TSTAMP")
		public OffsetDateTime offsetDateTime;

		@Zone(idPropertyName = "zoneId")
		@Column("TSTAMP")
		public OffsetDateTime offsetDateTime2;

		@Zone(offset = "+01:00")
		@Column("TSTAMP")
		public OffsetTime offsetTime;

		@Zone(offsetPropertyName = "zoneOffset")
		@Column("TSTAMP")
		public OffsetTime offsetTime2;

		@Zone(offset = "+03:00")
		@Column("TSTAMP")
		public ZonedDateTime zonedDateTime;

		@Zone(offsetPropertyName = "zoneOffset2")
		@Column("TSTAMP")
		public ZonedDateTime zonedDateTime2;

		public String getZoneId() {
			return "HST";
		}

		public String getZoneOffset() {
			return "+02:00";
		}

		public String getZoneOffset2() {
			return "+04:00";
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

	@Table("EMP")
	public static class TimeBean2 {
		@Column("EMPNO")
		public int id;

		@Column("HIREDATE")
		public Date date;

		@Column("HIREDATE")
		public Timestamp timestamp;

		@Column("HIREDATE")
		public Instant instant;

		@Column("HIREDATE")
		public LocalDate localDate;

		@Column("HIREDATE")
		public LocalDateTime localDateTime;

		@Column("HIREDATE")
		public LocalTime localTime;

		@Column("HIREDATE")
		public Year year;

		@Column("HIREDATE")
		public YearMonth yearMonth;

		@Column("HIREDATE")
		public MonthDay monthDay;

		@Zone(id = "EST")
		@Column("HIREDATE")
		public OffsetDateTime offsetDateTime;

		@Zone(idPropertyName = "zoneId")
		@Column("HIREDATE")
		public OffsetDateTime offsetDateTime2;

		@Zone(offset = "+01:00")
		@Column("HIREDATE")
		public OffsetTime offsetTime;

		@Zone(offsetPropertyName = "zoneOffset")
		@Column("HIREDATE")
		public OffsetTime offsetTime2;

		@Zone(offset = "+03:00")
		@Column("HIREDATE")
		public ZonedDateTime zonedDateTime;

		@Zone(offsetPropertyName = "zoneOffset2")
		@Column("HIREDATE")
		public ZonedDateTime zonedDateTime2;

		public String getZoneId() {
			return "HST";
		}

		public String getZoneOffset() {
			return "+02:00";
		}

		public String getZoneOffset2() {
			return "+04:00";
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
}
