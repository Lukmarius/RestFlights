package com.codecool.restflights.Service.Implementations;

import com.codecool.restflights.Controller.RoutesRestController;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class aiming to put logs into console or file.
 * Direction and options set in application.properties
 */
public class LoggingDispatcherServlet extends DispatcherServlet {

    private final Logger logger = Logger.getLogger(getClass());

//    dispatching requests and responses into logs
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }
        HandlerExecutionChain handler = getHandler(request);

        try {
            super.doDispatch(request, response);
            response.setHeader("Access-Control-Allow-Origin", "*");
        } finally {
            log(request, response, handler);
            updateResponse(response);
        }
    }

    private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache, HandlerExecutionChain handler) {

//        creating log String
        StringBuilder log = new StringBuilder();

        log.append(" ---- ");
        log.append(responseToCache.getStatus());
        log.append(" ---- ");
        log.append(requestToCache.getMethod());
        log.append(" ---- ");
        log.append(requestToCache.getRequestURI());
//        log.append(" - Header: ");
//        log.append(requestToCache.getHeaders("Authorization"));
//        log.append(requestToCache.getRemoteAddr());
//        log.append(" ---- ");
//        log.append(handler.toString());
//        log.append(" ---- ");
//        log.append(getResponsePayload(responseToCache));
        if ( ! requestToCache.getRequestURI().equals("/favicon.ico")){
            logger.info(log);
        }
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }


//    private String getResponsePayload(HttpServletResponse response) {
//        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
//        if (wrapper != null) {
//
//            byte[] buf = wrapper.getContentAsByteArray();
//            if (buf.length > 0) {
//                int length = Math.min(buf.length, 5120);
//                try {
//                    return new String(buf, 0, length, wrapper.getCharacterEncoding());
//                }
//                catch (UnsupportedEncodingException ex) {
//                    // NOOP
//                }
//            }
//        }
//        return "[unknown]";
//    }

}
