package com.bjike.session;


import com.bjike.entity.user.User;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 用户登陆session管理类
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class UserSession {
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("token令牌不能为空");
    private static Logger logger = LoggerFactory.getLogger(UserSession.class);

    private UserSession() {
    }

    private static final LoadingCache<String, User> SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.DAYS)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, User>() {
                @Override
                public void onRemoval(RemovalNotification<String, User> notification) {
                    if (!notification.getCause().equals(RemovalCause.REPLACED)) {
                        logger.info("remove:" + notification.getKey());
                    }
                }
            })
            .build(new CacheLoader<String, User>() {
                @Override
                public User load(String key) throws Exception {
                    return null;
                }
            });



    public static void put(String userId, User user) {
        if (StringUtils.isNotBlank(userId)) {
            SESSION.put(userId, user);
        } else {
            throw TOKEN_NOT_NULL;
        }


    }


    public static void remove(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            SESSION.invalidate(userId);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static User get(String userId) {
        try {
            if (StringUtils.isNotBlank(userId)) {
                return SESSION.get(userId);
            }
            throw TOKEN_NOT_NULL;
        } catch (Exception e) {
            return null;
        }

    }



}
