package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.dto.chat.FriendDTO;
import com.bjike.entity.chat.Friend;
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
public interface FriendSer extends Ser<Friend, FriendDTO> {
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
     * 添加好友
     * @param to
     * @throws SerException
     */
    default void add(FriendTO to) throws SerException {
    }

    /**
     * 删除好友
     * @param friendId
     * @throws SerException
     */
    default void delete(String friendId) throws SerException {
    }

    /**
     * 更改备注
     * @param friendId
     * @param remark
     * @throws SerException
     */
    default void editRemark(String friendId, String remark) throws SerException {

    }

    /**
     * 同意好友申请
     * @param friendId
     * @throws SerException
     */
    default void agree(String friendId) throws SerException {
    }

    /**
     * 拒绝好友申请
     * @param friendId
     * @throws SerException
     */
    default void refuse(String friendId) throws SerException {
    }
    /**
     * 用户所有朋友列表
     *
     * @param userId
     * @throws SerException
     */
    default List<FriendVO> list(String userId) throws SerException {
        return null;
    }


    /**
     * 好友类型查询列表
     * @param type
     * @param userId
     * @return
     * @throws SerException
     */
    default List<FriendVO> findByApplyType(ApplyType type, String userId) throws SerException {
        return null;
    }

    /**
     * 通过群id查询成员
     *
     * @param groupId
     * @return
     * @throws SerException
     */
    default List<FriendVO> groupMember(String groupId) throws SerException {
        return null;
    }

    /**
     * 朋友分组成员
     * @param id
     * @return
     * @throws SerException
     */
    default List<FriendVO> friendGroup(String id) throws SerException {
        return null;
    }

}
