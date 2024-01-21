package com.practice.reflectionapi.common;

import com.practice.reflectionapi.annotation.Controller;
import com.practice.reflectionapi.handler.HandlerKey;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}
