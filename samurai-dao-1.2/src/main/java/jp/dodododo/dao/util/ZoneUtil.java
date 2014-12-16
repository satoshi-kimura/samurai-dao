package jp.dodododo.dao.util;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ZoneUtil {

	private static Map<ZoneId, ZoneOffset> ZONE_OFFSETS = new HashMap<>();

	public static ZoneOffset zoneOffset(ZoneId zoneId) {
		if (ZONE_OFFSETS.containsKey(zoneId)) {
			return ZONE_OFFSETS.get(zoneId);
		}

		String id = zoneId.getId();
		if (id.startsWith("+") || id.startsWith("-")) {
			ZoneOffset zoneOffset = ZoneOffset.of(id);
			ZONE_OFFSETS.put(zoneId, zoneOffset);
			return zoneOffset;
		}

		String zone = ZonedDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("ZZZZ"));
		ZoneOffset zoneOffset = ZoneOffset.of(zone.substring(3, zone.length()));
		ZONE_OFFSETS.put(zoneId, zoneOffset);

		return zoneOffset;
	}

	public static ZoneId zoneId(String zoneId) {
		return ZoneId.of(zoneId, ZoneId.SHORT_IDS);
	}

	public static ZoneId zoneId(ZoneOffset zoneOffset) {
		return zoneOffset;
	}
}
