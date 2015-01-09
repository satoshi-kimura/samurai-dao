package jp.dodododo.dao.types;

import static org.junit.Assert.*;

import java.io.File;
import java.io.RandomAccessFile;

import jp.dodododo.dao.util.TmpFileUtil;

import org.junit.Test;

public class JavaTypesTest {

	@Test
	public void testINTEGER() throws Exception {
		Integer integer = JavaTypes.INTEGER.convert("11.00");
		assertEquals(11, integer.intValue());
	}

	@Test
	public void testBOOLEAN() throws Exception {
		assertEquals(true, JavaTypes.BOOLEAN.convert("true"));
		assertEquals(false, JavaTypes.BOOLEAN.convert("false"));
		assertEquals(true, JavaTypes.BOOLEAN.convert("1"));
		assertEquals(false, JavaTypes.BOOLEAN.convert("0"));
		assertEquals(true, JavaTypes.BOOLEAN.convert(1));
		assertEquals(false, JavaTypes.BOOLEAN.convert(0));
	}

	@Test
	public void testFileToByteArray() throws Exception {
		File file = TmpFileUtil.createTempFile();
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
		randomAccessFile.writeBytes("abc");
		randomAccessFile.close();
		byte[] bs = JavaTypes.BYTE_ARRAY.convert(file);
		assertEquals(3, bs.length);
		assertEquals('a', bs[0]);
		assertEquals('b', bs[1]);
		assertEquals('c', bs[2]);
		TmpFileUtil.deleteThreadLocalTmpFiles();
	}
}
