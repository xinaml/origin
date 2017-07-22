package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.dto.chat.FriendDTO;
import com.bjike.entity.chat.Friend;
import com.bjike.entity.chat.FriendGroup;
import com.bjike.ser.Ser;
import com.bjike.to.chat.FriendTO;
import com.bjike.type.chat.ApplyType;
import com.bjike.vo.chat.FriendGroupVO;
import com.bjike.vo.chat.FriendVO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 10:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IFriendSer extends Ser<Friend, FriendDTO> {
    /**
     * 组信息
     *
     * @param userId
     * @throws SerException
     */
    default List<FriendGroupVO> groupInfo(String userId) throws SerException {
        return null;
    }

    /**
     * 朋友列表
     *
     * @param userId
     * @throws SerException
     */
    default List<FriendVO> list(String userId) throws SerException {
        return null;
    }

    default void add(FriendTO to) throws SerException {
    }

    default void delete(String friendId,String userId) throws SerException {
    }

    default void editRemark(String friendId,String remark,String userId) throws SerException {

    }

    default void agree(String friendId,String userId) throws SerException {
    }
    default void refuse(String friendId,String userId) throws SerException {
    }
    default List<FriendVO> findByApplyType(ApplyType type, String userId) throws SerException {
        return  null;
    }
}
