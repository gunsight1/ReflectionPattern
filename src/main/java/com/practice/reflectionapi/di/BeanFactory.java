package com.practice.reflectionapi.di;

import com.practice.reflectionapi.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClass;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClass) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        this.preInstantiatedClass = preInstantiatedClass;
        initialize(); //beans 초기화
    }

    private void initialize() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Class<?> clazz : preInstantiatedClass){
            Object instance = createInstance(clazz);
            beans.put(clazz,instance);
        }
    }

    private Object createInstance(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = findConstructor(clazz);   //생성자 땡겨옴

        List<Object> parameters = new ArrayList<>();
        for(Class<?> typeClass : constructor.getParameterTypes()){  //매개변수 땡겨옴
            parameters.add(getParameterByClass(typeClass));
        }

        return constructor.newInstance(parameters.toArray());   //생성자와 매개변수를 인스턴스화
    }

    private Object getParameterByClass(Class<?> typeClass) throws InvocationTargetException, InstantiationException, IllegalAccessException {    //있으면 인스턴스로 던지고 없으면 만들어서 던짐
        Object instance = getBean(typeClass);
        if(Objects.nonNull(instance)){ return instance; }

        return createInstance(typeClass);
    }

    private Constructor<?> findConstructor(Class<?> clazz) {

        Constructor<?> injectedConstractors = BeanFactoryUtils.getInjectedConstractors(clazz);
        if (Objects.nonNull(injectedConstractors)){ return injectedConstractors; }

        return clazz.getConstructors()[0];
    }

    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }
}
