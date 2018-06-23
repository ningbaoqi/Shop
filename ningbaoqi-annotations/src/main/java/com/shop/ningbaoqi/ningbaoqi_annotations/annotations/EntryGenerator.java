package com.shop.ningbaoqi.ningbaoqi_annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target(ElementType.TYPE)用在类上的
 * @Retention(RetentionPolicy.SOURCE)编译期间进行处理，不影响运行时性能
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {
    String packageName();

    Class<?> entryTemplete();
}
