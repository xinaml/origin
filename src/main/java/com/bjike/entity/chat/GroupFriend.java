package com.bjike.entity.chat;

/**
 * 朋友分组
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupFriend {
    private String name;//分组名
    private String userId;//归属人

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
