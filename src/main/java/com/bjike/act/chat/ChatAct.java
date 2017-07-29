package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Msg;
import com.bjike.ser.chat.ChatSer;
import com.bjike.session.ChatSession;
import com.bjike.type.chat.MsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-19 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("chat")
public class ChatAct {
    @Autowired
    private ChatSer chatSer;
    @RequestMapping(value = "online", method = RequestMethod.GET)
    @ResponseBody
    public Result online() {
        List<Client> chatClients = new ArrayList<>();
        Client c = new Client();
        c.setHeadPath("xx");
        c.setUsername("阿斯大赛");
        chatClients.add(c);
        return ActResult.initialize(chatClients);
    }

    @RequestMapping(value = "quit/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result quit(@PathVariable String userId) throws ActException{
        try {
            Msg msg = new Msg();
            msg.setMsgType(MsgType.OFFLINE);
            msg.setContent("下线通知");
            msg.setUserId(userId);
            chatSer.broadcast(msg);
            ChatSession.remove(userId);
            return ActResult.initialize("quit success");
        }catch (SerException e){
            throw  new ActException(e.getMessage());
        }

    }

}
