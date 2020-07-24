package com.jetsen.aop;

import java.lang.annotation.*;

/**
 * @author: Nickel Fang
 * @date: 2020/7/6 13:18
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Time {
}
