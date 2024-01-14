package com.practice.reflectionapi.common;

import com.practice.reflectionapi.annotation.Controller;
import com.practice.reflectionapi.handler.RequestMappingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DispatcherServlet이 모든 웹 클라이언트의 요청을 받고 처리함.
 **/
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandler mapper;


    @Override //Generic Servlet
    public void init() throws ServletException {
        mapper = new RequestMappingHandler();
        mapper.init();
    }

    /**
     * 클라이언트 요청이 오면
     **/
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        log.info("DispatcherServlet##Service");

        try {
            Controller handler = mapper.findHandler(request.getRequestURI());
            String viewName = handler.handleReqeust(request, response);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName);
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            log.error("error :: ", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        
    }
}
