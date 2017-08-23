package com.bjike.type.user;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 08:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum SexType {

    UNKNOWN(0),
    MAN(1),

    WOMAN(2),;
    /**
     * 删除
     */
    private int code;

    SexType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
