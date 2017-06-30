package com.bjike.vo.comment;

import com.bjike.type.comment.ScoreType;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-29 09:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommentDetailsVO {
    /**
     * 点评id
     */
    private String id;
    /**
     * 点评图
     */
    private String[] images;
    /**
     * 点评内容
     */
    private String content;
    /**
     * 评分
     */
    private ScoreType scoreType;
    /**
     * 点评日期
     */
    private LocalDateTime createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
