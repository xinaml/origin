package com.bjike.entity.user;

import com.bjike.entity.BaseEntity;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 15:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class User extends BaseEntity {
    private String tu_id;
    private String token;
    private String nickname;
    private Boolean online;
    private String headPath;

    public String getTu_id() {
        return tu_id;
    }

    public void setTu_id(String tu_id) {
        this.tu_id = tu_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }
}
