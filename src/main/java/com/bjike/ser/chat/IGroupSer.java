package com.bjike.ser.chat;

import com.bjike.common.exception.SerException;
import com.bjike.dto.chat.GroupDTO;
import com.bjike.entity.chat.Group;
import com.bjike.ser.Ser;
import com.bjike.to.chat.GroupTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 10:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IGroupSer extends Ser<Group, GroupDTO> {

    default void add(GroupTO to) throws SerException{

    }


    default void edit(GroupTO to) throws SerException{

    }
}
