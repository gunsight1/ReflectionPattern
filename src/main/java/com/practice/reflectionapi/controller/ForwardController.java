package com.practice.reflectionapi.controller;

import com.practice.reflectionapi.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    
    private final String forwordUriPath;

    public ForwardController(String forwordUriPath) {
        this.forwordUriPath = forwordUriPath;
    }

    @Override
    public String handleReqeust(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }
}
