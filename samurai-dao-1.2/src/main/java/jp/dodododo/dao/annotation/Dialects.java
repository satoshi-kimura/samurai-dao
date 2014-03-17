package jp.dodododo.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jp.dodododo.dao.dialect.Default;
import jp.dodododo.dao.dialect.Dialect;

@Inherited
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Dialects {
	String[] value();

	Class<? extends Dialect>[] dialect() default Default.class;
}
