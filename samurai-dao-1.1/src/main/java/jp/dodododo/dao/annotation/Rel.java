package jp.dodododo.dao.annotation;

public @interface Rel {
	String table();

	String column();

	String property();
}
