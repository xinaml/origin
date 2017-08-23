package com.bjike.ser.chat.mongo;

import com.bjike.common.exception.SerException;
import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Msg;
import com.bjike.mongo.service.MoGoSer;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 16:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface MsgSer extends MoGoSer<Msg, MsgDTO> {
    default List<Msg> pointMsg(MsgDTO dto) throws SerException {
        return null;
    }
    default List<Msg> groupMsg(MsgDTO dto) throws SerException {
        return null;
    }
}
