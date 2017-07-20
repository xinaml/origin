package com.bjike.dao.chat.mongo;

import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Msg;
import com.bjike.mongo.dao.RepImpl;
import org.springframework.stereotype.Repository;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-20 16:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Repository
public class MsgRepImpl extends RepImpl<Msg,MsgDTO> implements  MsgRep{
}
