package com.practice.reflectionapi.common;

import com.practice.reflectionapi.adapter.HandlerAdapter;
import com.practice.reflectionapi.adapter.ModelAndView;
import com.practice.reflectionapi.adapter.SimpleControllerHandlerAdapter;
import com.practice.reflectionapi.enums.RequestMethod;
import com.practice.reflectionapi.handler.AnnotationHandlerMapping;
import com.practice.reflectionapi.handler.HandlerKey;
import com.practice.reflectionapi.handler.RequestMappingHandler;
import com.practice.reflectionapi.view.JspViewResolver;
import com.practice.reflectionapi.view.View;
import com.practice.reflectionapi.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * DispatcherServlet이 모든 웹 클라이언트의 요청을 받고 처리함.
 **/
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;

    private List<ViewResolver> viewResolvers;

    private List<HandlerAdapter> handlerAdapters;


    @Override //Generic Servlet
    public void init() {
        RequestMappingHandler requestMappingHandler = new RequestMappingHandler();
        requestMappingHandler.init();

        AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("com.pratice.reflectionapi");
        annotationHandlerMapping.initialize();

        handlerMappings = List.of(requestMappingHandler, annotationHandlerMapping);

        viewResolvers = Collections.singletonList(new JspViewResolver());
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdaptor());
    }

    /**
     * 클라이언트 요청이 오면
     **/
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
        log.info("DispatcherServlet##Service");

        String requestURI = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        try {
            //Object handler = handlerMappings.findHandler(new HandlerKey(RequestMethod.valueOf(request.getMethod()), request.getRequestURI())); -> 애노테이션 핸들러 맵핑 없이 단건처리일때.
            Object handler = handlerMappings.stream().filter(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
                    .map(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("핸들러 없음. :: " + requestMethod + " and " + requestURI));

            //String viewName = handler.handleReqeust(request, response); 핸들러 어댑터를 통해 맵핑처리함으로 역할이 사라짐.

            HandlerAdapter handlerAdapter = handlerAdapters.stream().filter(ha -> ha.supports(handler))
                    .findFirst().orElseThrow(() -> new ServletException("핸들러에 대한 어댑터 없음. -> " + handler));

            ModelAndView mav = handlerAdapter.handle(request, response, handler);

            for (ViewResolver viewResolver : viewResolvers){ // view Resolver에게 viewName을 전달하면 적절하게 view를 선택하여 render 해줌.
                View view = viewResolver.resolveView(mav.getViewName());
                view.render(ModelAndView.getModel(), request, response);
            }

        } catch (Exception e) {
            log.error("error :: ", e.getMessage());
            throw new RuntimeException(e);
        }
        
    }
}
