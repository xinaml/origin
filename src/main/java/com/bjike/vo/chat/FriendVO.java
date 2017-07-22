package com.bjike.vo.chat;

import com.bjike.type.chat.ApplyType;
import com.bjike.vo.BaseVO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 13:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FriendVO extends BaseVO {

    /**
     * 朋友名
     */
    private String nickname;
    /**
     * 朋友备注
     */
    private String remark;
    /**
     * 朋友头像
     */
    private String headPath;
    /**
     * 朋友所属分组
     */
    private String friendGroupId;

    private Boolean online;

    private ApplyType applyType;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getFriendGroupId() {
        return friendGroupId;
    }

    public void setFriendGroupId(String friendGroupId) {
        this.friendGroupId = friendGroupId;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public ApplyType getApplyType() {
        return applyType;
    }

    public void setApplyType(ApplyType applyType) {
        this.applyType = applyType;
    }
}
