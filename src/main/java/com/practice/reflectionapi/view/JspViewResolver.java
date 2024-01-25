package com.practice.reflectionapi.view;

import static com.practice.reflectionapi.view.RedirectVIew.DEFAULT_SUBSTRING_PREFIX;

public class JspViewResolver implements ViewResolver {

    @Override
    public View resolveView(String viewName) {
        if(viewName.startsWith(DEFAULT_SUBSTRING_PREFIX)){
            return new RedirectVIew(viewName);
        }
        return new JspView(viewName + ".jsp"); // 공통 suffix 처리
    }
}
