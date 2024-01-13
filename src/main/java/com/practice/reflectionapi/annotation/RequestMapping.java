package com.practice.reflectionapi.annotation;

import com.practice.reflectionapi.enums.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default ""; //url-path , 아무 입력이 없다면 빈 문자열

    RequestMethod[] method() default {};

}
