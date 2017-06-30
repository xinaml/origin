package com.bjike.type.comment;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ScoreType {
    /**
     * 1星
     */
    FIRST(0),
    /**
     * 2星
     */
    SECOND(1),
    /**
     * 3星
     */
    THIRD(2),
    /**
     * 4星
     */
    FOURTH(3),
    /**
     * 5星
     */
    FIFTH(4),;

    private int code;

    ScoreType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
