package com.bjike.ser.chat.mongo;

import com.bjike.common.exception.SerException;
import com.bjike.dto.Restrict;
import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Msg;
import com.bjike.mongo.service.MoGoSerImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 16:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class MsgSerImpl extends MoGoSerImpl<Msg, MsgDTO> implements MsgSer {
    @Override
    public List<Msg> pointMsg(MsgDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("senderId", dto.getUserId()));
        dto.getConditions().add(Restrict.eq("receiver", dto.getReviver()));
        return super.findByCis(dto);
    }

    @Override
    public List<Msg> groupMsg(MsgDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("senderId", dto.getUserId()));
        dto.getConditions().add(Restrict.eq("group", dto.getGroupId()));
        return super.findByCis(dto);
    }
}
