package com.bjike.entity.comment;

import com.bjike.entity.BaseEntity;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 17:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "comment_picture")
public class Picture extends BaseEntity{
    /**
     * 点评
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "comment_id", columnDefinition = "VARCHAR(36) COMMENT '点评id' ", nullable = false)
    private Comment comment;
    /**
     * 点评图片路径
     */
    @Column(columnDefinition = "VARCHAR(150) COMMENT '点评图片路径' ", nullable = false)
    private String path;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
