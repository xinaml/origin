package com.bjike.entity.chat;

import com.bjike.entity.BaseEntity;
import com.bjike.type.chat.ApplyType;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "chat_friend")
public class Friend extends BaseEntity {
    /**
     * 归属人
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '归属人id' ", nullable = false)
    private String userId;
    /**
     * 朋友id
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '朋友id' ", nullable = false)
    private String friendId;

    /**
     * 朋友备注
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '朋友备注' ")
    private String remark;
    /**
     * 朋友所在分组
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "friend_group_id", columnDefinition = "VARCHAR(36) COMMENT '朋友所在分组id' ")
    private FriendGroup friendGroup;

    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '申请状态'", nullable = false, insertable = false)
    private ApplyType applyType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public FriendGroup getFriendGroup() {
        return friendGroup;
    }

    public void setFriendGroup(FriendGroup friendGroup) {
        this.friendGroup = friendGroup;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ApplyType getApplyType() {
        return applyType;
    }

    public void setApplyType(ApplyType applyType) {
        this.applyType = applyType;
    }
}
