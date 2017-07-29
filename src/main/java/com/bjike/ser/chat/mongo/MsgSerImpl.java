package com.bjike.ser.chat.mongo;

import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Msg;
import com.bjike.mongo.service.MoGoSerImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 16:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class MsgSerImpl extends MoGoSerImpl<Msg, MsgDTO> implements MsgSer {
}
