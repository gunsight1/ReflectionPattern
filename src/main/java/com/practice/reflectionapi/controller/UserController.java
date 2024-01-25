package com.practice.reflectionapi.controller;

import com.practice.reflectionapi.annotation.Inject;
import com.practice.reflectionapi.annotation.MVCController;
import com.practice.reflectionapi.service.UserService;

@MVCController
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService(){
        return userService;
    }
}
