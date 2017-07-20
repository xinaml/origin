package com.bjike.to.chat;

import com.bjike.to.BaseTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 10:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupTO extends BaseTO {
    private String name;

    private String userId;//创建人

    private String description;//群描述

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
