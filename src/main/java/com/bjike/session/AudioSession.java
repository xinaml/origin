package com.bjike.session;

import com.bjike.common.exception.SerException;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.concurrent.TimeUnit;

/**语音session管理
 * @Author: [liguiqin]
 * @Date: [2017-08-02 15:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AudioSession {
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("userId不能为空");
    private static Logger logger = LoggerFactory.getLogger(ShareSession.class);

    private AudioSession() {
    }

    private static final LoadingCache<String, Session> AUDIO_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.HOURS)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, Session>() {
                @Override
                public void onRemoval(RemovalNotification<String, Session> notification) {
                    if (!notification.getCause().equals(RemovalCause.REPLACED)) {
                        logger.info("remove:" + notification.getKey());
                    }
                }
            })
            .build(new CacheLoader<String, Session>() {
                @Override
                public Session load(String key) throws Exception {
                    return null;
                }
            });


    /**
     * 新增用户会话信息
     *
     * @param userId  令牌值
     * @param session 登录用户信息
     * @return 是否已经登录
     */
    public static void put(String userId, Session session) {
        if (StringUtils.isNotBlank(userId)) {
            AUDIO_SESSION.put(userId, session);
        } else {
            throw TOKEN_NOT_NULL;
        }


    }

    /**
     * 根据令牌删除用户会话信息
     *
     * @param userId 登录令牌
     * @return 是否删除成功
     */
    public static void remove(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            AUDIO_SESSION.invalidate(userId);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static Session get(String userId) {
        try {
            if (StringUtils.isNotBlank(userId)) {

                return AUDIO_SESSION.get(userId);

            }
            throw TOKEN_NOT_NULL;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取用户会话总数
     *
     * @return 总数
     */
    public static long count() {
        return AUDIO_SESSION.size();
    }

    /**
     * 获取全部用户会话信息
     *
     * @return 会话信息集合
     */
    public static LoadingCache<String, Session> sessions() {
        if (null != AUDIO_SESSION && AUDIO_SESSION.size() > 0) {
            return AUDIO_SESSION;
        }
        return null;
    }

    public static boolean exists(String userId) throws SerException {
        try {
            return AUDIO_SESSION.getUnchecked(userId) != null;
        } catch (Exception e) {
            return false;
        }
    }

}
