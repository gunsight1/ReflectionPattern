package com.practice.reflectionapi.adapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private Object view;
    private static Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewName) {
        view = viewName;
    }

    public static Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
    }

    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null);
    }
}
