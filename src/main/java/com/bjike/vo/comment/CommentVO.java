package com.bjike.vo.comment;

import com.bjike.to.BaseTO;
import com.bjike.type.comment.ScoreType;
import com.bjike.type.comment.VisibleType;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommentVO extends BaseTO {
    /**
     * 点评人
     */
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public VisibleType getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(VisibleType visibleType) {
        this.visibleType = visibleType;
    }
}
