package com.practice.reflectionapi.controller;

import com.practice.reflectionapi.annotation.Controller;
import com.practice.reflectionapi.model.User;
import com.practice.reflectionapi.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller {

    @Override
    public String handleReqeust(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
        return "redirect:/users";
    }
}
