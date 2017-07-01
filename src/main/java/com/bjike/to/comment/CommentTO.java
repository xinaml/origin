package com.bjike.to.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import com.bjike.type.comment.ScoreType;
import com.bjike.type.comment.VisibleType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CommentTO extends BaseTO {
    /**
     * 点评人
     */
    private String userId;

    /**
     * 点评内容
     */
    @NotBlank(message = "请填写点评内容", groups = {ADD.class, EDIT.class})
    private String content;
    /**
     * 评分
     */
    private ScoreType scoreType;
    /**
     * 经纬X坐标
     */
    @NotBlank(message = "请填写地点id", groups = {ADD.class, EDIT.class})
    private String pointId;

    /**
     * 经纬X坐标
     */
    @NotBlank(message = "请填写经纬X坐标", groups = {ADD.class, EDIT.class})
    private String pointX;
    /**
     * 经纬Y坐标
     */
    @NotBlank(message = "请填写经纬Y坐标", groups = {ADD.class, EDIT.class})
    private String pointY;
    /**
     * 店铺名
     */
    @NotNull(message = "请填写店铺名", groups = {ADD.class, EDIT.class})
    private String shopName;
    /**
     * 详细地址
     */
    private String address;

    /**
     * 可见范围
     */
    @NotNull(message = "请填写可见范围", groups = {ADD.class, EDIT.class})
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

    public String getPointX() {
        return pointX;
    }

    public void setPointX(String pointX) {
        this.pointX = pointX;
    }

    public String getPointY() {
        return pointY;
    }

    public void setPointY(String pointY) {
        this.pointY = pointY;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public VisibleType getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(VisibleType visibleType) {
        this.visibleType = visibleType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
}
