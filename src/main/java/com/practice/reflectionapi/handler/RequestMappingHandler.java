package com.practice.reflectionapi.handler;

import com.practice.reflectionapi.annotation.Controller;
import com.practice.reflectionapi.common.HandlerMapping;
import com.practice.reflectionapi.controller.ForwardController;
import com.practice.reflectionapi.controller.UserCreateController;
import com.practice.reflectionapi.controller.UserListController;
import com.practice.reflectionapi.enums.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandler implements HandlerMapping {
    private Map<String, Controller> mappings = new HashMap<>();

   public void init(){

       //mappings.put(new HandlerKey(RequestMethod.GET, "/").toString(), new HomeController());
       mappings.put(new HandlerKey(RequestMethod.GET, "/users").toString(), new UserListController());
       mappings.put(new HandlerKey(RequestMethod.POST, "/users").toString(), new UserCreateController());
       mappings.put(new HandlerKey(RequestMethod.GET, "/user/form").toString(), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey){

       return mappings.get(handlerKey);
    }
}
