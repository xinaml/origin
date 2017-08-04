package com.bjike.session;

import com.bjike.common.exception.SerException;
import com.bjike.entity.chat.AudioClient;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 语音请求session管理类
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-03 10:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AudioClientSession {
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("userId不能为空");
    private static Logger logger = LoggerFactory.getLogger(ShareSession.class);

    private AudioClientSession() {
    }

    private static final LoadingCache<String, AudioClient> AUDIO_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.HOURS)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, AudioClient>() {
                @Override
                public void onRemoval(RemovalNotification<String, AudioClient> notification) {
                    if (!notification.getCause().equals(RemovalCause.REPLACED)) {
                        logger.info("remove:" + notification.getKey());
                    }
                }
            })
            .build(new CacheLoader<String, AudioClient>() {
                @Override
                public AudioClient load(String key) throws Exception {
                    return null;
                }
            });


    /**
     * 新增用户会话信息
     *
     * @param userId 令牌值
     * @param client 登录用户信息
     * @return 是否已经登录
     */
    public static void put(String userId, AudioClient client) {
        if (StringUtils.isNotBlank(userId)) {
            AUDIO_SESSION.put(userId, client);
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


    public static AudioClient get(String userId) {
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
    public static LoadingCache<String, AudioClient> clients() {
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
