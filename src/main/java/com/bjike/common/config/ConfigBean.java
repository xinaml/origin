package com.bjike.common.config;

import com.bjike.common.interceptor.login.LoginIntercept;
import com.bjike.ser.user.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-07 09:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class ConfigBean {
    @Autowired
    private IUserSer userSer;

    @Bean
    public Object init(){
        LoginIntercept.userSer = userSer;
        return new Object();
    }
}
