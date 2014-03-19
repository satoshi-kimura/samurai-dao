package jp.dodododo.dao.handler.impl;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import jp.dodododo.dao.annotation.Column;
import jp.dodododo.dao.columns.ResultSetColumn;
import jp.dodododo.dao.util.ClassUtil;
import jp.dodododo.dao.util.Sun14ReflectionUtil;

import org.junit.Test;

public class BeanResultSetHandlerTest {

	@Test
	public void testGetUsableConstructor() {
		Class<Target> targetClass = Target.class;
		List<Constructor<Target>> constructors = ClassUtil.getConstructors(targetClass, Modifier.PUBLIC);
		List<ResultSetColumn> resultSetColumnList = new ArrayList<ResultSetColumn>();
		Constructor<Target> constructor = BeanResultSetHandler.getUsableConstructor(targetClass, constructors, resultSetColumnList, targetClass);
		assertEquals(2, constructor.getParameterTypes().length);
	}

	@Test
	public void testGetUsableConstructor2() throws Exception {
		Class<Target2> targetClass = Target2.class;
		List<Constructor<Target2>> constructors = ClassUtil.getConstructors(targetClass, Modifier.PUBLIC);
		List<ResultSetColumn> resultSetColumnList = new ArrayList<ResultSetColumn>();
		Constructor<Target2> constructor = BeanResultSetHandler.getUsableConstructor(targetClass, constructors, resultSetColumnList, targetClass);
		if (Sun14ReflectionUtil.canUse() == true) {
			assertNotNull(constructor);
			assertEquals(0, constructor.getParameterTypes().length);
			assertNotNull(constructor.newInstance(new Object[0]));
		} else {
			assertNull(constructor);
		}
	}

	public static class Target {
		public Target() {
		}

		public Target(int i) {
		}

		public Target(@Column("i") int i, @Column("j") int j) {
		}

		public Target(int i, @Column("j") int j, int k) {
		}
	}

	public static class Target2 {
		private Target2(int i) {
			throw new RuntimeException();
		}
	}
}
