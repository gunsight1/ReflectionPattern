package com.practice.reflectionapi.controller;

import com.practice.reflectionapi.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller //사용자 정의 애노테이션 부여
public class HomeController implements Controller {
    @Override
    public String handleReqeust(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }


//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home (HttpServletRequest request, HttpServletResponse response){
//        return "home";
//    }
}
