package com.bjike.ser.chat;

import com.alibaba.fastjson.JSON;
import com.bjike.common.exception.SerException;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Msg;
import com.bjike.entity.user.User;
import com.bjike.ser.user.IUserSer;
import com.bjike.session.ChatSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:02]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
@Component
public class ChatServer {


    @Autowired
    public static IUserSer userSer;

    // OnPen，连接创建时调用的方法
    @OnOpen
    public void join(Session session) {
        String sid = this.getSession(session).getId();
        Client client = initChatClient(session, sid);
        if(null!=client){
            ChatSession.put(sid, client); //更新刷新后的websocket session
        }

    }

    // OnClose，连接关闭时调用的方法
    @OnClose
    public void quit(Session session, CloseReason reason) {
        System.out.println(reason.getCloseCode() + "-----" + reason.getCloseCode().getCode());
        if (reason.getCloseCode().getCode() != 1001) {//刷新
            String sid = this.getSession(session).getId();
            ChatSession.remove(sid);
        }


    }

    // OnMessage，传输信息过程中调用的方法
    @OnMessage
    public void incoming(String content, Session session) throws SerException {
        session.setMaxTextMessageBufferSize(1024 * 1024 * 1024);
        Msg msg = JSON.parseObject(content, Msg.class);
        User sender = userSer.findById(null);
        if (null != sender) {
            content.replaceAll("操", "*");
            msg.setContent(msg.getContent());
            msg.setSenderHeadPath(sender.getHeadPath());
            msg.setSenderName(sender.getNickname());
            broadcast(msg);
        }
    }

    // OnError，发生错误时调用的方法
    @OnError
    public void onError(Throwable t, Session session) throws Throwable {
        /**
         * 非正常关闭浏览器
         */
        ChatSession.remove(this.getSession(session).getId());
        System.out.println("Chat Error: " + t.toString());
    }

    //    broadcast(String msg)，通过connections，对所有其他用户推送信息的方法
    private static void broadcast(Msg msg) {
        boolean point = false;
        switch (msg.getMsgType()) {
            case POINT:
                //  point = true;
                break; //findUser
            case GROUP:
                break; //findGroup
            case PUB:
                break; //all
        }

    }

    private Client initChatClient(Session session, String sid) {
        try {
            if (!ChatSession.exists(sid)) {
                Client client = new Client();
                User sender = userSer.findById(null);
                client.setUsername(sender.getNickname());
                client.setHeadPath(sender.getHeadPath());
                client.setSid(sid);
                return  client;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpSession getSession(Session session) {
        return (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
    }

}
