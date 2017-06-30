package com.bjike.common.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public final class RequestContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestContext.class);

    private RequestContext(){}

    public static HttpServletRequest get(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//springmvc 自带
    }

}
