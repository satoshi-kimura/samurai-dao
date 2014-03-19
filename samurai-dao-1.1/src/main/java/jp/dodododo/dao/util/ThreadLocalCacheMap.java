package jp.dodododo.dao.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ThreadLocalCacheMap<K, V> implements Map<K, V> {

	private ThreadLocal<Map<Object, Object>> threadLocalCaches = new ThreadLocal<Map<Object, Object>>() {
		@Override
		protected Map<Object, Object> initialValue() {
			return new HashMap<Object, Object>();
		}
	};

	public void clear() {
		ThreadLocalUtil.remove(threadLocalCaches);
	}

	public boolean containsKey(Object key) {
		return getMap().containsKey(key);
	}

	public boolean containsValue(Object value) {
		return getMap().containsValue(value);
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return getMap().entrySet();
	}

	@SuppressWarnings("unchecked")
	private Map<K, V> getMap() {
		return (Map<K, V>) threadLocalCaches.get();
	}

	public V get(Object key) {
		return getMap().get(key);
	}

	public boolean isEmpty() {
		return getMap().isEmpty();
	}

	public Set<K> keySet() {
		return getMap().keySet();
	}

	public V put(K key, V value) {
		return getMap().put(key, value);
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		getMap().putAll(m);
	}

	public V remove(Object key) {
		return getMap().remove(key);
	}

	public int size() {
		return getMap().size();
	}

	public Collection<V> values() {
		return getMap().values();
	}

	@Override
	public boolean equals(Object o) {
		return getMap().equals(o);
	}

	@Override
	public int hashCode() {
		return getMap().hashCode();
	}
}
