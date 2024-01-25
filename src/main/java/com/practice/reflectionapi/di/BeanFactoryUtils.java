package com.practice.reflectionapi.di;

import com.practice.reflectionapi.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    public static Constructor<?> getInjectedConstractors(Class<?> clazz) {
        Set<Constructor> injectedConstractors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotations(Inject.class));//@Inject가 부여 된 생성자만 가져옴

        if(injectedConstractors.isEmpty()){
            return null;
        }
        return injectedConstractors.iterator().next();
    }
}
