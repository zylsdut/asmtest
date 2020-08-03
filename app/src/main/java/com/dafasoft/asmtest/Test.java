package com.dafasoft.asmtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangyulong
 * @date 2020/8/3.
 * Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Test {
}
