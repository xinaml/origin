package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.dto.chat.FriendGroupDTO;
import com.bjike.entity.chat.FriendGroup;
import com.bjike.ser.Ser;
import com.bjike.to.chat.FriendGroupTO;
import com.bjike.vo.chat.FriendVO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-21 10:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FriendGroupSer extends Ser<FriendGroup, FriendGroupDTO> {

    /**
     * 添加朋友分组
     * @param to
     * @throws SerException
     */
    default void add(FriendGroupTO to) throws SerException {
    }

    /**
     * 编辑朋友分组
     * @param to
     * @throws SerException
     */
    default void edit(FriendGroupTO to) throws SerException {
    }
}
