package com.practice.reflectionapi.common;

import com.practice.reflectionapi.adapter.HandlerAdapter;
import com.practice.reflectionapi.adapter.ModelAndView;
import com.practice.reflectionapi.handler.AnnotationHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerAdaptor implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationHandler;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((AnnotationHandler) handler).handle(request, response);
        return new ModelAndView(viewName);
    }
}
