package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Msg;

import javax.websocket.Session;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 14:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ChatSer {

    /**
     * 初始化客户端
     *
     * @param userId
     * @param session
     * @return
     * @throws SerException
     */
    default Boolean initClient(String userId, Session session) throws SerException {
        return null;
    }

    /**
     * 推送消息
     *
     * @param msg
     * @throws SerException
     */
    default void broadcast(Msg msg) throws SerException {

    }


    /**
     * 读取离线时未接收到的消息
     *
     * @param userId
     * @throws SerException
     */
    default void readOffLineMsg(String userId) throws SerException {

    }
}
