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

    public static ScoreType getCode(int code){
        ScoreType type = null;
        switch (code){
            case 0:type=ScoreType.FIFTH;break;
            case 1:type=ScoreType.SECOND;break;
            case 2:type=ScoreType.THIRD;break;
            case 3:type=ScoreType.FOURTH;break;
            case 4:type=ScoreType.FIFTH;break;
            default: type=ScoreType.FIFTH;break;
        }
        return  type;
    }
}
