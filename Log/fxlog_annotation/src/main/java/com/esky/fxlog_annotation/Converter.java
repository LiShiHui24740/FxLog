package com.esky.fxlog_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author AirLand
 * @time on 2019-09-27 14:42
 * @email lish_air@163.com
 * @jianshu https://www.jianshu.com/u/816932948905
 * @gitHub https://github.com/LiShiHui24740
 * @describe:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Converter {
    String name();
}
