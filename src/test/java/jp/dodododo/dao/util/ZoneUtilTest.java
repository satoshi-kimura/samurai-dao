package jp.dodododo.dao.util;

import static jp.dodododo.dao.util.ZoneUtil.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ZoneUtilTest {

	@Test
	public void testZoneOffset() {
		assertEquals("+09:00", zoneOffset(zoneId("Asia/Tokyo")).getId());
		assertEquals("+09:00", zoneOffset(zoneId("+9")).getId());
		assertEquals("+09:00", zoneOffset(zoneId("JST")).getId());

		assertEquals("-08:00", zoneOffset(zoneId("America/Los_Angeles")).getId());
		assertEquals("-08:00", zoneOffset(zoneId("US/Pacific")).getId());
		assertEquals("-09:00", zoneOffset(zoneId("America/Anchorage")).getId());
		assertEquals("-10:00", zoneOffset(zoneId("-10")).getId());
		assertEquals("+01:00", zoneOffset(zoneId("ECT")).getId());
	}
}