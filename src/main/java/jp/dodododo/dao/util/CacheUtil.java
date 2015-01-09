package jp.dodododo.dao.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Satoshi Kimura
 */
public abstract class CacheUtil {

	public static <K, V> Map<K, V> cacheMap() {
		return cacheMap(new HashMap<K, V>(1024));
	}
	public static <V> Map<String, V> cacheCaseInsensitiveMap() {
		return cacheMap(new CaseInsensitiveMap<V>(1024));
	}

	public static <K, V> Map<K, V> cacheMap(Map<K, V> map) {
		Map<K, V> cacheMap = Collections.synchronizedMap(map);
		cacheMapList.add(cacheMap);
		return cacheMap;
	}

	private static List<Map<?, ?>> cacheMapList = Collections.synchronizedList(new LinkedList<Map<?, ?>>());

	private static List<List<?>> cacheListList = Collections.synchronizedList(new LinkedList<List<?>>());

	private static List<CacheObject<?>> cacheObjectList = Collections.synchronizedList(new LinkedList<CacheObject<?>>());

	public static void clearAllCache() {
		for (Iterator<Map<?, ?>> iter = cacheMapList.iterator(); iter.hasNext();) {
			Map<?, ?> cacheMap = iter.next();
			cacheMap.clear();
		}
		cacheMapList.clear();

		for (Iterator<List<?>> iter = cacheListList.iterator(); iter.hasNext();) {
			List<?> cacheList = iter.next();
			cacheList.clear();
		}
		cacheListList.clear();

		for (Iterator<CacheObject<?>> iter = cacheObjectList.iterator(); iter.hasNext();) {
			CacheObject<?> cacheObject = iter.next();
			cacheObject.clear();
		}
		cacheObjectList.clear();
	}

	public static <T> List<T> cacheList() {
		return cacheList(new ArrayList<T>(1024));
	}

	public static <T> List<T> cacheList(List<T> list) {
		List<T> cacheList = Collections.synchronizedList(list);
		cacheListList.add(cacheList);
		return cacheList;
	}

	public static <T> CacheObject<T> cacheObject() {
		CacheObject<T> ret = new CacheObject<T>();
		cacheObjectList.add(ret);
		return ret;
	}
}
