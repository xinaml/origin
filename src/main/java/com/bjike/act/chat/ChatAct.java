package com.bjike.act.chat;

import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.entity.chat.Client;
import com.bjike.session.ChatSession;
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

    @RequestMapping(value = "online", method = RequestMethod.GET)
    @ResponseBody
    public Result online() {
        List<Client> chatClients = new ArrayList<>();
        return ActResult.initialize(chatClients);
    }

    @RequestMapping(value = "quit/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result quit(@PathVariable String userId) {
        ChatSession.remove(userId);
        return ActResult.initialize("quit success");
    }

}
