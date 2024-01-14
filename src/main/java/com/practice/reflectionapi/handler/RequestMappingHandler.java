package com.practice.reflectionapi.handler;

import com.practice.reflectionapi.annotation.Controller;
import com.practice.reflectionapi.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandler {
    private Map<String, Controller> mappings = new HashMap<>();

   public void init(){
        mappings.put("/", new HomeController());
    }

    public Controller findHandler(String urlPath){
        return mappings.get(urlPath);
    }
}
