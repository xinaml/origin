package com.bjike.common.restful;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-26 11:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface Result {
    /**
     * 消息码
     */
    int getCode();

    /**
     * 错误消息
     */
    String getMsg();

    /**
     * 返回数据
     */
    Object getData();

}
