package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 聊天群
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "ike_chat_group")
public class Group extends BaseEntity {
    @Column(columnDefinition = "VARCHAR(36) COMMENT '聊天室名称' ", nullable = false)
    private String name;

    @Column(columnDefinition = "VARCHAR(36) COMMENT '创建人' ", nullable = false)
    private String userId;//创建人

    @Column(columnDefinition = "VARCHAR(500) COMMENT '群描述' ", nullable = false)
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
