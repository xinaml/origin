package com.bjike.common.util;

import com.alibaba.fastjson.JSON;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.SerException;
import com.bjike.entity.user.User;
import com.bjike.redis.client.RedisClient;
import com.bjike.session.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-24 09:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserUtil {

    public static RedisClient redis;

    public static User currentUser(String token) throws SerException {
        return getUser(token);
    }

    public static User currentUser() throws SerException {
        HttpServletRequest request = getRequest();
        String token = request.getHeader(UserCommon.TOKEN);
        return getUser(token);

    }

    public static String currentUserID() throws SerException {
        return currentUser().getId();
    }

    private static User getUser(String token) throws SerException {
        if (null != token) {
            User loginUser = UserSession.get(token);
            if (null != loginUser) {
                return loginUser;
            } else { //redis 获取
                String loginUser_str = redis.getMap(UserCommon.LOGIN_USER, token.toString());
                if (StringUtils.isNotBlank(loginUser_str)) {
                    loginUser = JSON.parseObject(loginUser_str, User.class);
                    UserSession.put(token.toString(), loginUser); //设置到session
                    return loginUser;
                }
            }
            throw new SerException("登录已失效!");
        } else {
            throw new SerException("用户未登录!");
        }
    }

    /**
     * 获取当前线程的request以获取token
     *
     * @return
     */
    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
