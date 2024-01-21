package com.practice.reflectionapi.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RedirectVIew implements View {

    public static final String DEFAULT_SUBSTRING_PREFIX = "redirect:";
    private final String name;

    public RedirectVIew(String name) {
        this.name = name;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(name.substring(DEFAULT_SUBSTRING_PREFIX.length())); //redirect 문자 이후로 주소를 보냄.
    }
}
