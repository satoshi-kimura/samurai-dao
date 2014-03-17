package jp.dodododo.dao.util;

import static jp.dodododo.dao.util.StringUtil.*;

import java.io.InputStream;
import java.io.Reader;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDecamelize() {
		assertEquals("I", decamelize("i"));
		assertEquals("I", decamelize("I"));
		assertEquals("ID", decamelize("id"));
		assertEquals("ID", decamelize("ID"));
		assertEquals("FOO_BAR", decamelize("fooBar"));
	}

	public void testCamelize() {
		assertEquals("i", camelize("i"));
		assertEquals("i", camelize("I"));
		assertEquals("id", camelize("id"));
		assertEquals("id", camelize("ID"));
		assertEquals("fooBar", camelize("foo_Bar"));
		assertEquals("fooBar", camelize("Foo_Bar"));
		assertEquals("foobar", camelize("FooBar"));
	}

	public void testCapitalize() {
		assertEquals("I", capitalize("i"));
		assertEquals("I", capitalize("I"));
		assertEquals("Id", capitalize("id"));
		assertEquals("ID", capitalize("ID"));
		assertEquals("FooBar", capitalize("fooBar"));
		assertEquals("FooBar", capitalize("FooBar"));
		assertEquals("FooBAR", capitalize("FooBAR"));
		assertEquals("FOOBar", capitalize("FOOBar"));
	}

	public void testDecapitalize() {
		assertEquals("i", decapitalize("i"));
		assertEquals("i", decapitalize("I"));
		assertEquals("id", decapitalize("id"));
		assertEquals("ID", decapitalize("ID"));
		assertEquals("fooBar", decapitalize("FooBar"));
		assertEquals("fooBar", decapitalize("FooBar"));
		assertEquals("fooBAR", decapitalize("FooBAR"));
		assertEquals("FOOBar", decapitalize("FOOBar"));
	}

	public void testTrimLine() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = loader.getResourceAsStream("jp/dodododo/dao/util/big.txt");
		Reader reader = InputStreamReaderUtil.create(is, "UTF-8");
		String text = ReaderUtil.readText(reader);
		assertEquals("big", StringUtil.trimLine(text));
	}

	public void testLength() {
		assertEquals(0, StringUtil.length(""));
		assertEquals(8, StringUtil.length("aAzZ 190"));
		assertEquals(17, StringUtil.length("aAzZ190あいうえお"));
		assertEquals(11, StringUtil.length("サムライDAO"));
		assertEquals(7, StringUtil.length("ｻﾑﾗｲDAO"));
	}
}
