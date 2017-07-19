package com.bjike.entity.user;

/**
 * 朋友关系链
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-10 14:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FriendChain {
    /**
     * 关系链
     */
    private User friendChain = null;

    public User getFriendChain() {
        return friendChain;
    }

    public void setFriendChain(User friendChain) {
        this.friendChain = friendChain;
    }
}
