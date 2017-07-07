package com.bjike.vo.comment;

import com.bjike.type.comment.ScoreType;
import com.bjike.type.comment.VisibleType;
import com.bjike.vo.BaseVO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommentVO extends BaseVO {
    /**
     * 点评人
     */
    private String nickname;
    /**
     * 点评人头像
     */
    private String headPath;

    /**
     * 点评内容
     */
    private String content;
    /**
     * 评分
     */
    private ScoreType scoreType; /**

     * 可见范围
     */
    private VisibleType visibleType;

    /**
     * 点评图
     */
    private String[] images;

    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 是否已赞
     */
    private Boolean alreadyLikes;

    private String createTime;


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

    public VisibleType getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(VisibleType visibleType) {
        this.visibleType = visibleType;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Boolean getAlreadyLikes() {
        return alreadyLikes;
    }

    public void setAlreadyLikes(Boolean alreadyLikes) {
        this.alreadyLikes = alreadyLikes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
