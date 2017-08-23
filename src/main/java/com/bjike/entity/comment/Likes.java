package com.bjike.entity.comment;

import com.bjike.entity.BaseEntity;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-01 16:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "comment_likes")
public class Likes extends BaseEntity {
    /**
     * 点评
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "comment_id", columnDefinition = "VARCHAR(36) COMMENT '点评id' ", nullable = false)
    private Comment comment;
    /**
     * 点评人
     */
    @Column(columnDefinition = "VARCHAR(150) COMMENT 'userId' ", nullable = false)
    private String userId;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
