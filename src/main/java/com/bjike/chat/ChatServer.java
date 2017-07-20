package com.bjike.chat;

import com.alibaba.fastjson.JSON;
import com.bjike.common.exception.SerException;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Msg;
import com.bjike.ser.chat.IChatSer;
import com.bjike.session.ChatSession;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@ServerEndpoint(value = "/chat/{userId}", configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatServer {
    private static final int MAX_SIZE = 1024 * 1024 * 1024;
    public static IChatSer chatSer;


    // OnPen，连接创建时调用的方法
    @OnOpen
    public void join(@PathParam("userId") String userId, Session session) {
        try {
            Client client = chatSer.initChatClient(userId, session);
            if (null != client) {
                ChatSession.put(userId, client); //更新刷新后的websocket session
            }
        } catch (SerException e) {
            e.printStackTrace();
        }


    }

    // OnClose，连接关闭时调用的方法
    @OnClose
    public void quit(@PathParam("userId") String userId, CloseReason reason) {
        int code = reason.getCloseCode().getCode();
        if (code != 1000) {//刷新
            ChatSession.remove(userId);
        }


    }

    // OnMessage，传输信息过程中调用的方法
    @OnMessage
    public void incoming(@PathParam("userId") String userId, String content, Session session) throws SerException {
        session.setMaxTextMessageBufferSize(MAX_SIZE);
        Msg msg = JSON.parseObject(content, Msg.class);
        Client client = ChatSession.get(userId);
        if (null != client) {
            content.replaceAll("操", "*");
            msg.setContent(msg.getContent());
            msg.setSenderHeadPath(client.getHeadPath());
            msg.setSenderName(client.getUsername());
            chatSer.broadcast(msg,userId);
        }
    }

    // OnError，发生错误时调用的方法
    @OnError
    public void onError(@PathParam("userId") String userId, Throwable t, Session session) throws Throwable {
        /**
         * 非正常关闭浏览器
         */
        ChatSession.remove(userId);
    }


}
