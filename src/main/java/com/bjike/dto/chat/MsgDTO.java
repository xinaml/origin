package com.bjike.dto.chat;

import com.bjike.dto.BaseDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 16:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MsgDTO extends BaseDTO {
    private String reviver;
    private String groupId;

    public String getReviver() {
        return reviver;
    }

    public void setReviver(String reviver) {
        this.reviver = reviver;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
