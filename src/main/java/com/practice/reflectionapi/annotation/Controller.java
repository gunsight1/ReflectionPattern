package com.practice.reflectionapi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 요구사항 : @Controller 애노테이션이 설정 된 모든 클래스를 찾아서 출력한다.
 * **/
@Target(ElementType.TYPE) //클래스, 인터페이스, 애노테이션, Enum 선언에 힐 수 있다.
@Retention(RetentionPolicy.RUNTIME) //유지기간, Runtime
public @interface Controller {
}
