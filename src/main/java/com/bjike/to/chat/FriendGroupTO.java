package com.bjike.to.chat;

import com.bjike.to.BaseTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FriendGroupTO extends BaseTO {
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
