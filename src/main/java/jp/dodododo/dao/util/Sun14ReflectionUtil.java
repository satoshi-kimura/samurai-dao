package jp.dodododo.dao.util;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * see com.thoughtworks.xstream.converters.reflection.Sun14ReflectionProvider
 */
public class Sun14ReflectionUtil { // because this class use sun.reflect packages.

	private static Map<Class<?>, Constructor<?>> constructorCache = new HashMap<Class<?>, Constructor<?>>();

	public static <T> T newInstance(Class<T> type) {
		try {
			Constructor<T> customConstructor = getMungedConstructor(type);
			return customConstructor.newInstance(new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException("Cannot construct " + type.getName(), e);
		}
	}

	@SuppressWarnings({ "all" })
	public static <T> Constructor<T> getMungedConstructor(Class<T> type) {
		try {
			synchronized (constructorCache) {
				Constructor<?> c = constructorCache.get(type);
				if (c != null) {
					return (Constructor<T>) c;
				}
				sun.reflect.ReflectionFactory factory = sun.reflect.ReflectionFactory.getReflectionFactory();
				c = factory.newConstructorForSerialization(type, Object.class.getDeclaredConstructor(new Class[0]));
				constructorCache.put(type, c);
				return (Constructor<T>) c;
			}
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private static final String vendor = System.getProperty("java.vm.vendor");
	private static final float majorJavaVersion = getMajorJavaVersion();

	public static boolean canUse() {
		return (isSun() || isOracle() || isApple() || isHPUX() || isIBM() || isBlackdown() || isBEAWithUnsafeSupport() || isHitachi() || isSAP() || isDiablo())
				&& is14() && loadClass("sun.misc.Unsafe") != null;
	}

	static final float DEFAULT_JAVA_VERSION = 1.3f;

	private static final float getMajorJavaVersion() {
		try {
			return isAndroid() ? 1.5f : Float.parseFloat(System.getProperty("java.specification.version"));
		} catch (NumberFormatException e) {
			// Some JVMs may not conform to the x.y.z java.version format
			return DEFAULT_JAVA_VERSION;
		}
	}

	private static boolean is14() {
		return majorJavaVersion >= 1.4f;
	}

	private static boolean isAndroid() {
		return vendor.indexOf("Android") != -1;
	}

	private static boolean isSun() {
		return vendor.indexOf("Sun") != -1;
	}

	private static boolean isOracle() {
		return vendor.indexOf("Oracle") != -1;
	}

	private static boolean isApple() {
		return vendor.indexOf("Apple") != -1;
	}

	private static boolean isHPUX() {
		return vendor.indexOf("Hewlett-Packard Company") != -1;
	}

	private static boolean isIBM() {
		return vendor.indexOf("IBM") != -1;
	}

	private static boolean isBlackdown() {
		return vendor.indexOf("Blackdown") != -1;
	}

	private static boolean isDiablo() {
		return vendor.indexOf("FreeBSD Foundation") != -1;
	}

	private static boolean isHitachi() {
		return vendor.indexOf("Hitachi") != -1;
	}

	private static boolean isSAP() {
		return vendor.indexOf("SAP AG") != -1;
	}

	// private static boolean isHarmony() {
	// return vendor.indexOf("Apache Software Foundation") != -1;
	// }

	/*
	 * Support for sun.misc.Unsafe and sun.reflect.ReflectionFactory is present in JRockit versions R25.1.0 and later, both 1.4.2 and 5.0 (and in
	 * future 6.0 builds).
	 */

	private static boolean isBEAWithUnsafeSupport() {
		// This property should be "BEA Systems, Inc."
		if (vendor.indexOf("BEA") != -1) {

			/*
			 * Recent 1.4.2 and 5.0 versions of JRockit have a java.vm.version string starting with the "R" JVM version number, i.e.
			 * "R26.2.0-38-57237-1.5.0_06-20060209..."
			 */
			String vmVersion = System.getProperty("java.vm.version");
			if (vmVersion.startsWith("R")) {
				/*
				 * We *could* also check that it's R26 or later, but that is implicitly true
				 */
				return true;
			}

			/*
			 * For older JRockit versions we can check java.vm.info. JRockit 1.4.2 R24 -> "Native Threads, GC strategy: parallel" and JRockit 5.0 R25
			 * -> "R25.2.0-28".
			 */
			String vmInfo = System.getProperty("java.vm.info");
			if (vmInfo != null) {
				// R25.1 or R25.2 supports Unsafe, other versions do not
				return (vmInfo.startsWith("R25.1") || vmInfo.startsWith("R25.2"));
			}
		}
		// If non-BEA, or possibly some very old JRockit version
		return false;
	}

	private static Map<String, Class<?>> loaderCache = new HashMap<String, Class<?>>();

	private static Class<?> loadClass(String name) {
		Class<?> cached = loaderCache.get(name);
		if (cached != null) {
			return cached;
		}
		try {
			Class<?> clazz = Class.forName(name, true, Sun14ReflectionUtil.class.getClassLoader());
			loaderCache.put(name, clazz);
			return clazz;
		} catch (LinkageError e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}