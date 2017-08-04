package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.entity.chat.AudioClient;
import com.bjike.entity.chat.Client;
import com.bjike.entity.chat.Msg;
import com.bjike.ser.chat.ChatSer;
import com.bjike.session.AudioClientSession;
import com.bjike.session.AudioSession;
import com.bjike.session.ChatSession;
import com.bjike.type.chat.MsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Result quit(@PathVariable String userId) throws ActException {
        try {
            Msg msg = new Msg();
            msg.setMsgType(MsgType.OFFLINE);
            msg.setContent("下线通知");
            msg.setUserId(userId);
            chatSer.broadcast(msg);
            ChatSession.remove(userId);
            AudioSession.remove(userId);
            return new ActResult("quit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    @RequestMapping(value = "join/audio/{sender}/{reviver}", method = RequestMethod.GET)
    @ResponseBody
    public Result join(@PathVariable String sender, @PathVariable String reviver) throws ActException {
        AudioClient client = AudioClientSession.get(sender);
        if (null != client) {
            Map<String, Boolean> map = client.getRequester();
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                if (entry.getValue().equals(reviver)) {
                    entry.setValue(true);
                    client.setRequester(map);
                    return ActResult.initialize("join success");
                }
            }
        }
        return new ActResult("audio is over");
    }

    @RequestMapping(value = "quit/audio/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result quitAudio(@PathVariable String userId) throws ActException {
        AudioClientSession.remove(userId);
        AudioSession.remove(userId);
        return new ActResult("audio is over");
    }

}
