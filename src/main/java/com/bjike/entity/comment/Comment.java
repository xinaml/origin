package com.bjike.entity.comment;

import com.bjike.entity.BaseEntity;
import com.bjike.type.comment.ScoreType;
import com.bjike.type.comment.VisibleType;

import javax.persistence.*;


/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    /**
     * 点评人
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '点评人' ", nullable = false)
    private String userId;
    /**
     * 店铺
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "shop_id", columnDefinition = "VARCHAR(36) COMMENT '店铺id' ", nullable = false)
    private Shop shop;
    /**
     * 点评内容
     */
    @Column(columnDefinition = "VARCHAR(200) COMMENT '点评内容' ", nullable = false)
    private String content;
    /**
     * 评分
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '评分'")
    private ScoreType scoreType;

    /**
     * 点赞量
     */
    @Column(columnDefinition = "INT(8) default 0  COMMENT '点赞量' " ,insertable = false)
    private Integer likes;
    /**
     * 可见范围
     */
    @Column(columnDefinition = "TINYINT(2) COMMENT '可见范围'")
    private VisibleType visibleType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public VisibleType getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(VisibleType visibleType) {
        this.visibleType = visibleType;
    }
}
