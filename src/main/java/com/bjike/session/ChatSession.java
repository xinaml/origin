package com.bjike.session;

import com.bjike.common.exception.SerException;
import com.bjike.entity.chat.Client;
import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 聊天session管理
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ChatSession {
    private static final RuntimeException TOKEN_NOT_NULL = new RuntimeException("userId不能为空");
    private static Logger logger = LoggerFactory.getLogger(ShareSession.class);

    private ChatSession() {
    }

    private static final LoadingCache<String, Client> CHAT_SESSION = CacheBuilder.newBuilder()
            .expireAfterWrite(7, TimeUnit.DAYS)
            .maximumSize(1000)
            .removalListener(new RemovalListener<String, Client>() {
                @Override
                public void onRemoval(RemovalNotification<String, Client> notification) {
                    if (!notification.getCause().equals(RemovalCause.REPLACED)) {
                        logger.info("remove:" + notification.getKey());
                    }
                }
            })
            .build(new CacheLoader<String, Client>() {
                @Override
                public Client load(String key) throws Exception {
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
    public static void put(String userId, Client client) {
        if (StringUtils.isNotBlank(userId)) {
            CHAT_SESSION.put(userId, client);
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
            CHAT_SESSION.invalidate(userId);
        } else {

            throw TOKEN_NOT_NULL;
        }
    }


    public static Client get(String userId) {
        try {
            if (StringUtils.isNotBlank(userId)) {
                return CHAT_SESSION.get(userId);
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
        return CHAT_SESSION.size();
    }

    /**
     * 获取全部用户会话信息
     *
     * @return 会话信息集合
     */
    public static LoadingCache<String, Client> sessions() {
        if (null != CHAT_SESSION && CHAT_SESSION.size() > 0) {
            return CHAT_SESSION;
        }
        return null;
    }

    public static boolean exists(String userId) throws SerException {
        try {
            return CHAT_SESSION.getUnchecked(userId) != null;
        } catch (Exception e) {
            return false;
        }
    }


}
