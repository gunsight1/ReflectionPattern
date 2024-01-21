package com.practice.reflectionapi.view;

/** view name을 받아 view send를 결정 **/
public interface ViewResolver {
    View resolveView(String viewName);
}
