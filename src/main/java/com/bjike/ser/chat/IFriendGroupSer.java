package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.dto.chat.FriendGroupDTO;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.FriendGroup;
import com.bjike.entity.user.User;
import com.bjike.ser.Ser;
import com.bjike.to.chat.FriendGroupTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 10:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IFriendGroupSer extends Ser<FriendGroup, FriendGroupDTO> {

    default void add(FriendGroupTO to) throws SerException {
    }

    default void edit(FriendGroupTO to) throws SerException {
    }
}
