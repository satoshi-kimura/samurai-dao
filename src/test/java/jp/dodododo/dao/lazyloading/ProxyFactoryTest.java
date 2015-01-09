package jp.dodododo.dao.lazyloading;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class ProxyFactoryTest extends ProxyFactory {

	@Test
	public void testCreateClassOfT() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			SecurityException, InvocationTargetException, NoSuchMethodException {
		ProxyFactory factory = new ProxyFactory();

		NotAuto notAuto = factory.create(NotAuto.class);
		assertEquals(0, notAuto.i);
	}

	@Test
	public void testCreateClassOfTConstructorOfTObjectArray() throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException {
		ProxyFactory factory = new ProxyFactory();

		NotAuto notAuto = factory.create(NotAuto.class, NotAuto.class.getConstructor(Integer.TYPE), 100);
		assertEquals(100, notAuto.i);
	}

	@Test
	public void testNewInstance() {
		ProxyFactory factory1 = ProxyFactory.newInstance(NotAuto.class);
		assertEquals(ProxyFactory.class, factory1.getClass());

		try {
			ProxyFactory.newInstance(Auto.class);
			fail();
		} catch (Exception success) {
		}
	}

	public static class NotAuto implements LazyLoadingProxy<NotAuto> {

		public int i;

		public NotAuto() {

		}

		public NotAuto(int i) {
			this.i = i;
		}

		public NotAuto lazyLoad() {
			return null;
		}

		public NotAuto real() {
			return null;
		}

		public void setReal(NotAuto real) {
		}

	}

	public static class Auto implements AutoLazyLoadingProxy<Auto> {

		public Auto lazyLoad() {
			return null;
		}

		public Auto real() {
			return null;
		}

		public void setReal(Auto real) {
		}
	}

}
