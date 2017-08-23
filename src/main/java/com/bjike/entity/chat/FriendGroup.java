package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 朋友分组
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "chat_friend_group")
public class FriendGroup extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(36) COMMENT '朋友分组名' ", nullable = false)
    private String name;//分组名
    @Column(columnDefinition = "VARCHAR(36) COMMENT '归属人' ", nullable = false)
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
