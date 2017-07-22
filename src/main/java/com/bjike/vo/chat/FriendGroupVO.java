package com.bjike.vo.chat;

import com.bjike.vo.BaseVO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 13:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FriendGroupVO extends BaseVO{
    private String name;
    private Integer counts;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
