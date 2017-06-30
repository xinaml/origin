package com.bjike.common.interceptor;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.bjike.common.interceptor.error.ErrorRequestInterceptor;
import com.bjike.common.interceptor.limit.SmoothBurstyInterceptor;
import com.bjike.common.interceptor.login.LoginIntercept;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-01-15 10:10]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter4();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(fastJsonHttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ErrorRequestInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LoginIntercept()).addPathPatterns("/**");
        registry.addInterceptor(new SmoothBurstyInterceptor(1000, SmoothBurstyInterceptor.LimitType.DROP)).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
