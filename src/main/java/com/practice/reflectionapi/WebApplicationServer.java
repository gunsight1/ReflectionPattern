package com.practice.reflectionapi;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Ctrl + Alt S -> 빌드 실행 배포 -> 빌드 도구 -> Gradle
 * Default Build Tool을 Gradle -> Intelli IDEA로 변경
 * Ctrl + Alt + Shift S -> 모듈 -> 경로
 * ~ /프로젝트명/out/ ..... 을 webapps/WEB-INF/classes로 변경
 * 빌드 후 실행
 **/
public class WebApplicationServer {

    private static Logger logger = LoggerFactory.getLogger(WebApplicationServer.class);
    public static void main(String[] args) throws LifecycleException {
        String webappDirLocation = "webapps/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        logger.info("path :: ", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();


    }
}
