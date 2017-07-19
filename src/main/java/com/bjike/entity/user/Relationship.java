package com.bjike.entity.user;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-10 14:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Relationship {

    /**
     * 关系链列表
     */
    private List <List<FriendChain>> friendChains = new ArrayList<>();
    private boolean yourFriend =false;

    public List<List<FriendChain>> getFriendChains() {
        return friendChains;
    }

    public void setFriendChains(List<List<FriendChain>> friendChains) {
        this.friendChains = friendChains;
    }

    public boolean isYourFriend() {
        return yourFriend;
    }

    public void setYourFriend(boolean yourFriend) {
        this.yourFriend = yourFriend;
    }
}
