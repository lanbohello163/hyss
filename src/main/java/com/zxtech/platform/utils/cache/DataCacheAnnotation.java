package com.zxtech.platform.utils.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存时间默认为30秒
 * 使用方式：@DataCacheAnnotation(50)
 * */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataCacheAnnotation {
	int value() default 30;
	String[] tables() default {};
}
