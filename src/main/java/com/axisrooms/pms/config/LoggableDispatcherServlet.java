package com.axisrooms.pms.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private final Log logger = LogFactory.getLog(getClass());

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
        LogMessage log = new LogMessage(
                res.getStatus(),
                req.getMethod(),
                req.getRequestURI() + ((req.getQueryString() != null) ? ("?" + req.getQueryString()) : ""),
                req.getRemoteAddr(),
                getRequestPayload(req),
                getResponsePayload(res));
        logger.info(log);
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

    private static class LogMessage {
        private int httpStatus;
        private String httpMethod;
        private String path;
        private String clientIp;
        private String request;
        private String response;

        public LogMessage(int httpStatus, String httpMethod, String path, String clientIp, String request, String response) {
            this.httpStatus = httpStatus;
            this.httpMethod = httpMethod;
            this.path = path;
            this.clientIp = clientIp;
            this.request = request;
            this.response = response;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("\n\n%-8s :  ", "STATUS")).append(httpStatus)
                    .append(String.format("\n%-8s :  ", "METHOD")).append(httpMethod)
                    .append(String.format("\n%-8s :  ", "PATH")).append(path)
                    .append(String.format("\n%-8s :  ", "REQUEST")).append(request)
                    .append(String.format("\n%-8s :  ", "RESPONSE")).append(response)
                    .append(String.format("\n%-8s :  ", "IP")).append(clientIp).append("\n");
            return sb.toString();
        }
    }

}