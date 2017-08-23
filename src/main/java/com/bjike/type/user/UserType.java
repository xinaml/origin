package com.bjike.type.user;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 11:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum UserType {
    VIP(0),//VIP
    NORMAL(1),; //普通成员


    private int code;

    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
