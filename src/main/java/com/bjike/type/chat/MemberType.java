package com.bjike.type.chat;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-22 11:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum  MemberType {
    MASTER(0), //群主
    MANAGER(1),//管理员
    ORDINARY(2),;//普通的
    private int value;

    MemberType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
