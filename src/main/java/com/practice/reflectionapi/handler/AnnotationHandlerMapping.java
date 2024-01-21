package com.practice.reflectionapi.handler;

import com.practice.reflectionapi.annotation.Controller;
import com.practice.reflectionapi.annotation.MVCController;
import com.practice.reflectionapi.annotation.RequestMapping;
import com.practice.reflectionapi.common.HandlerMapping;
import com.practice.reflectionapi.enums.RequestMethod;
import javassist.tools.reflect.Reflection;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);

        Set<Class<?>> typesAnnotatedWithController = reflections.getTypesAnnotatedWith(MVCController.class);

        typesAnnotatedWithController.forEach(clazz -> Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);
                    Arrays.stream(getRequestMethods(requestMapping))
                          .forEach(requestMethod -> handlers.put(
                                  new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod) //
                          ));
                })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }
}
