package com.bjike.dao.chat;

import com.bjike.dao.JpaRep;
import com.bjike.dto.chat.FriendDTO;
import com.bjike.dto.chat.FriendGroupDTO;
import com.bjike.entity.chat.Friend;
import com.bjike.entity.chat.FriendGroup;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 11:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FriendRep  extends JpaRep<Friend, FriendDTO> {
}
