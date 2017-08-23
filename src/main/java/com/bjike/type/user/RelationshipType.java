package com.bjike.type.user;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 09:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum RelationshipType {
    //    两者关系(0亲人1同事2校友3同乡4同行)
    RELATIVE(0),//亲戚
    WORKMATE(1),//同事
    CLASSMATE(2),//校友
    FELLOW(3),//同乡
    PEER(4),;//同行
    private int code;

    RelationshipType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
