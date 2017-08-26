package com.bjike.session;

import com.bjike.common.exception.SerException;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 注册邀请码session
 * 注册验证码session
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-12 09:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class AuthCodeSession {
    private static Logger logger = LoggerFactory.getLogger(AuthCodeSession.class);

    private AuthCodeSession() {
    }

    private static final RuntimeException SID_NOT_NULL = new RuntimeException("sid不能为空");

    private static final LoadingCache<String, String> SID_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    logger.info("remove:" + notification.getCause().name());
                }
            })
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return null;
                }
            });


    public static void put(String sid, String code) throws SerException {
        if (StringUtils.isNotBlank(sid)) {
            SID_SESSION.put(sid, code);
        } else {
            throw SID_NOT_NULL;
        }
    }


    public static void remove(String sid) {
        if (StringUtils.isNotBlank(sid)) {
            SID_SESSION.invalidate(sid);
        } else {

            throw SID_NOT_NULL;
        }
    }


    public static String get(String sid) {
        try {
            if (StringUtils.isNotBlank(sid)) {
                return SID_SESSION.get(sid);
            }
        } catch (Exception e) {
            return null;
        }

        throw SID_NOT_NULL;
    }

}
