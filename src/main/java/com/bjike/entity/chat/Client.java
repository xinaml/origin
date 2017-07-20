package com.bjike.entity.chat;

import javax.websocket.Session;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Client {
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像
     */
    private String headPath;

    /**
     * 会话
     */
    private Session session;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
