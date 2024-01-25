package com.practice.reflectionapi.controller;

import com.practice.reflectionapi.annotation.MVCController;
import com.practice.reflectionapi.annotation.ReflectionController;
import com.practice.reflectionapi.annotation.RequestMapping;
import com.practice.reflectionapi.enums.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller //사용자 정의 애노테이션 부여
@MVCController
public class HomeController{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleReqeust(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }


//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home (HttpServletRequest request, HttpServletResponse response){
//        return "home";
//    }
}
