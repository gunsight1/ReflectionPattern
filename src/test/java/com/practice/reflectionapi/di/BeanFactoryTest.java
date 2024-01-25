package com.practice.reflectionapi.di;

import com.practice.reflectionapi.annotation.MVCController;
import com.practice.reflectionapi.annotation.Service;
import com.practice.reflectionapi.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() throws InvocationTargetException, InstantiationException, IllegalAccessException {
       reflections = new Reflections("com.practice");
       //UserController, UserService
       Set<Class<?>>preInstantiatedClass = getTypesAnnotatedWith(MVCController.class, Service.class);
       beanFactory = new BeanFactory(preInstantiatedClass);
    }

    /**
     * 각 애노테이션이 붙은 클래스 타입 객체를 조회 - 컨트롤러, 서비스 순으로 작동
     **/private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> ... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for(Class<? extends Annotation> annotation : annotations){
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}