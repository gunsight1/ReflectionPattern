package com.practice.reflectionapi.controller;

import com.practice.reflectionapi.annotation.Controller;
import com.practice.reflectionapi.annotation.RequestMapping;
import com.practice.reflectionapi.enums.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller //사용자 정의 애노테이션 부여
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home (HttpServletRequest request, HttpServletResponse response){
        return "home";
    }
}
