package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;

import javax.persistence.*;

/**
 * 群成员
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "chat_group_member")
public class GroupMember extends BaseEntity {
    /**
     * 归属群
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "group_id", columnDefinition = "VARCHAR(36) COMMENT '群id' ", nullable = false)
    private Group group;
    /**
     * 群成员id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '群成员id' ", nullable = false)
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
