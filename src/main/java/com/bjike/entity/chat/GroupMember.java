package com.bjike.entity.chat;

/**
 * 朋友分组成员
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupMember {
    private Group group;
    private String userId;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
