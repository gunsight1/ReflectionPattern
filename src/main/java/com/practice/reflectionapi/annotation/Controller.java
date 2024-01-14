package com.practice.reflectionapi.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    String handleReqeust(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
