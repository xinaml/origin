package com.bjike.type.chat;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-19 14:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  MsgType {
    PUB(0), //公共
    POINT(1),//点对点
    GROUP(2),;//群发
    private int value;

    MsgType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
