package com.bjike.entity.chat;

import com.bjike.type.chat.MsgType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Msg {
    /**
     * 发送人
     */
    private String senderName;
    /**
     * 发送人id
     */
    private String senderId;
    /**
     * 发送人头像
     */
    private String senderHeadPath;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 消息类型
     */
    private MsgType msgType = MsgType.POINT;//消息类型

    /**
     * 接收组
     */
    private String group;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderHeadPath() {
        return senderHeadPath;
    }

    public void setSenderHeadPath(String senderHeadPath) {
        this.senderHeadPath = senderHeadPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}