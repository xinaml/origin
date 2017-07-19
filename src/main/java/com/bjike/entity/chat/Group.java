package com.bjike.entity.chat;

import java.time.LocalDateTime;

/**
 * 聊天群
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 15:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Group {
    private String name;// 聊天室名称
    private String user;//创建人
    private String desc;//群描述
    private LocalDateTime createTime;// 创建时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
