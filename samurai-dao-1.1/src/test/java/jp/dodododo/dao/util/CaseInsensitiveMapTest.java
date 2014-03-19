package jp.dodododo.dao.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaseInsensitiveMapTest {

	@Test
	public void testPutGet() throws Exception {
		CaseInsensitiveMap<String> map = new CaseInsensitiveMap<String>();

		map.put("a", "b");
		assertEquals("b", map.get("a"));
		assertEquals("b", map.get("A"));

		map.put("b", "C");
		assertEquals("C", map.get("b"));
		assertEquals("C", map.get("B"));

		map.put("a_b", "x");
		assertEquals("x", map.get("a_b"));
		assertEquals("x", map.get("A_b"));
		assertEquals("x", map.get("a_B"));
		assertEquals("x", map.get("A_B"));
		assertEquals("x", map.get("aB"));

		map.put("bC", "y");
		assertEquals("y", map.get("b_c"));
		assertEquals("y", map.get("b_C"));
		assertEquals("y", map.get("B_c"));
		assertEquals("y", map.get("bC"));
		assertEquals("y", map.get("B_C"));
		assertEquals("y", map.get("b_c"));
	}
}
