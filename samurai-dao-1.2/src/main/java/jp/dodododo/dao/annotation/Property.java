package jp.dodododo.dao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jp.dodododo.dao.commons.Bool;

/**
 *
 * @author Satoshi Kimura
 */
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
	Bool readable() default Bool.UNDEFINED;

	Bool writable() default Bool.UNDEFINED;

	Class<? extends Throwable>[] ignoreExceptions() default {};
}
