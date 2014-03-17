package jp.dodododo.dao.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ThreadLocalCacheMapTest {

	@Test
	public void test() {
		ThreadLocalCacheMap<String, String> map = new ThreadLocalCacheMap<String, String>();
		assertNull(map.get("k"));

		map.put("k", "v");
		assertEquals("v", map.get("k"));

		map.clear();
		assertNull(map.get("k"));

		map.put("k", "v");
		assertEquals("v", map.get("k"));
	}

}
