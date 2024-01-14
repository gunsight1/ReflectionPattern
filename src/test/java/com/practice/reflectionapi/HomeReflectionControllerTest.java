package com.practice.reflectionapi;

import com.practice.reflectionapi.annotation.ReflectionController;
import com.practice.reflectionapi.annotation.Service;
import com.practice.reflectionapi.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Controller 애노테이션이 설정 된 모든 클래스를 찾아서 출력한다.
 **/
class HomeReflectionControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(HomeReflectionControllerTest.class);

    @Test
    void controllerScan(){
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(ReflectionController.class, Service.class));

        logger.debug("beans :: ", beans); //slf4j로는 왜 안나옴?
        System.err.println(beans); //println 정상 출력
    }

    /**
     * 인자를 Annotation 클래스를 슈퍼클래스로, 상속 부여 받은 하위 클래스 까지 포함하여 List 로 받음.
     * ? 은 와일드카드로 all, 모두를 뜻함.
     **/

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("com.practice.reflectionapi"); //prefix 하위 패키지 탐색

        Set<Class<?>> beans = new HashSet<>();

        /**
         * 매개변수(parameter) annotations를 반복(forEach)하여 수행
         * annotation은 인자 List를 사용할 변수 이름
         * beans 컬렉션 변수명에 addAll 모두 더함.
         * 무엇을? = reflections를 이용해 하위 패키지를 기준으로 24line의 전달인자(argument) .class 애노테이션이 붙은 모든 것들.
         * beans.addAll(reflections.getTypesAnnotatedWith(annotation)));
         **/
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }

    /**
     * User라는 이름의 Class를 클래스 리터럴(클래스의 정보를 보는 표기법) 으로 가져옴. 즉, 클래스를 사용함이 아니라, 클래스 정보를 가져옴.
     *
     *  Arrays.stream (***) 배열을 스트림으로 변환하는 메서드. 배열이나 컬렉션 등의 데이터를 함수형으로 처리함
     * int[] arr = {1, 2, 3, 4, 5}; 을 스트림으로 받는다면 ? -> Stream<Integer> stream = Arrays.stream(arr);
     *
     * getDeclared*** 은 선언된 하위항목들에 대해 가져옴.
     * .collect(Collectors.toList()): 은 스트림 요소들을 리스트에 수집함.
     * Collectors는 스트림을 다른 형태의 컬렉션으로 변환하는 클래스
     * Arrays (정적배열) ArrayList (가변배열)
     **/
    @Test
    void showClass() {
        Class<User> clasz = User.class;
        System.err.println(clasz.getName());
        System.err.println(" User All Declared fields :: " + Arrays.stream(clasz.getDeclaredFields()).collect(Collectors.toList()));
        System.err.println(" User All Declared Constructors ::  " + Arrays.stream(clasz.getDeclaredConstructors()).collect(Collectors.toList()));
        System.err.println(" User All Declared Methods ::  " + Arrays.stream(clasz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    /**
     * 힙(Heap) 영역에 로드 된 클래스 타입 객체 가져옴
     **/
    @Test
    void load() throws ClassNotFoundException {

        // 1안
        Class<User> clasz = User.class;

        // 2안
        User user = new User("test01", "이름임.");
        Class<? extends User> userClassInfo = user.getClass();

        // 3안
        Class<?> claszz = Class.forName("com.practice.reflectionapi.model.User");

        System.err.println("result 1 ::: " + clasz);
        System.err.println("result 2 ::: " + userClassInfo);
        System.err.println("result 3 ::: " + claszz);

        logger.debug("",clasz);
        logger.debug("",userClassInfo);
        logger.debug("",claszz);

        Assertions.assertThat(clasz == userClassInfo).isTrue();
        Assertions.assertThat(userClassInfo == claszz).isTrue();
        Assertions.assertThat(claszz == clasz).isTrue();

    }
}