package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.chat.MsgDTO;
import com.bjike.entity.chat.Msg;
import com.bjike.ser.chat.FriendSer;
import com.bjike.ser.chat.GroupSer;
import com.bjike.ser.chat.mongo.MsgSer;
import com.bjike.to.chat.GroupTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 群操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("chat/msg")
public class MsgAct {
    @Autowired
    private MsgSer msgSer;

    /**
     * Point 单独聊天消息记录
     * @param request
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "/point/{reviver}", method = RequestMethod.GET)
    @ResponseBody
    public Result pointMsg(MsgDTO dto,@PathVariable String reviver, HttpServletRequest request) throws ActException {
        try {
            String userId = request.getHeader("userId");
            dto.setReviver(reviver);
            dto.setUserId(userId);
            List<Msg> msgs = msgSer.pointMsg(dto);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * Group 群聊天消息记录
     * @param request
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    public Result groupMsg(MsgDTO dto,@PathVariable String groupId, HttpServletRequest request) throws ActException {
        try {
            String userId = request.getHeader("userId");
            dto.setGroupId(groupId);
            dto.setUserId(userId);
            List<Msg> msgs = msgSer.groupMsg(dto);
            return  ActResult.initialize(msgs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
