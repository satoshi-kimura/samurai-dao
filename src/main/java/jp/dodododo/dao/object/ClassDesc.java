package jp.dodododo.dao.object;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.dodododo.dao.util.ClassUtil;

public class ClassDesc<T> {

	private Class<T> targetClass;

	private Map<Integer, List<Constructor<T>>> constructors = Collections
			.synchronizedMap(new HashMap<Integer, List<Constructor<T>>>());

	private List<Method> methods = new ArrayList<Method>();

	public ClassDesc(Class<T> target) {
		this.targetClass = target;
		setUpMethods();
	}

	public List<Constructor<T>> getConstructors(int modifier) {
		List<Constructor<T>> ret = constructors.get(modifier);
		if (ret != null) {
			return ret;
		}
		ret = ClassUtil.getConstructors(targetClass, modifier);
		constructors.put(modifier, ret);
		return ret;
	}

	public <A extends Annotation> List<Constructor<T>> getAnnotatedConstructors(Class<A> annotationClass, int modifier) {
		List<Constructor<T>> ret = new ArrayList<Constructor<T>>();

		List<Constructor<T>> constructors = getConstructors(modifier);
		for (Constructor<T> constructor : constructors) {
			if (constructor.getAnnotation(annotationClass) != null) {
				ret.add(constructor);
			}
		}
		return ret;
	}

	public <A extends Annotation> List<Method> getAnnotatedMethods(Class<A> annotationClass, int modifier) {

		List<Method> ret = new ArrayList<Method>();
		List<Method> methods = getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(annotationClass) != null) {
				ret.add(method);
			}
		}
		return ret;
	}

	private List<Method> getMethods() {
		return this.methods;
	}

	private void setUpMethods() {
		this.methods = new ArrayList<Method>();
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			this.methods.add(method);
		}

		Method[] declaredMethods = targetClass.getDeclaredMethods();
		for (Method method : declaredMethods) {
			if (this.methods.contains(method)) {
				continue;
			}
			this.methods.add(method);
		}
	}

	public Class<T> getTargetClass() {
		return targetClass;
	}
}
