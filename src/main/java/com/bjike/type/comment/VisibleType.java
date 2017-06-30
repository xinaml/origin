package com.bjike.type.comment;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-29 11:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum VisibleType {
    /**
     * 所有人
     */
    ALL(0),
    /**
     * 朋友可见
     */
    FRIEND(1),;
    private int code;

    public int getCode() {
        return code;
    }

    VisibleType(int code) {
        this.code = code;
    }
}
