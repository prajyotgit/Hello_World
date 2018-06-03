package com.axisrooms.pms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;


public class LoggableDispatcherServlet extends DispatcherServlet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        try {
            super.doDispatch(request, response);
        } finally {
            log(request, response);
            updateResponse(response);
        }
    }

    private void log(HttpServletRequest req, HttpServletResponse res) {
        logger.info(
            String.format("\n\n%1$-8s : %2$s \n%3$-8s : %4$s \n%5$-8s : %6$s \n%7$-8s : %8$s \n%9$-8s : %10$s \n%11$-8s : %12$s \n",
            "STATUS"  , res.getStatus(),
            "METHOD"  , req.getMethod(),
            "PATH"    , req.getRequestURI() + ((req.getQueryString() != null) ? ("?" + req.getQueryString()) : ""),
            "REQUEST" , getRequestPayload(req),
            "RESPONSE", getResponsePayload(res),
            "IP"      , req.getRemoteAddr())
        );
    }

    private String getRequestPayload(HttpServletRequest req) {
        try {
            return req.getReader().lines().collect(Collectors.joining());
        } catch (IOException e) {
            return "[absent]";
        }
    }

    private String getResponsePayload(HttpServletResponse response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            byte[] buff = wrapper.getContentAsByteArray();
            if (buff.length > 0) {
                int length = Math.min(buff.length, 5120);
                try {
                    return new String(buff, 0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                }
            }
        }
        return "[absent]";
    }

    private void updateResponse(HttpServletResponse response) throws IOException {
        ContentCachingResponseWrapper responseWrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        responseWrapper.copyBodyToResponse();
    }

}