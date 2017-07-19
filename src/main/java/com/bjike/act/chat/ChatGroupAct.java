package com.bjike.act.chat;

import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("chat/group")
public class ChatGroupAct {
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Result add() {
        return ActResult.initialize(null);
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete() {
        return ActResult.initialize(null);
    }

    @RequestMapping(value = "edit", method = RequestMethod.PUT)
    @ResponseBody
    public Result edit() {
        return ActResult.initialize(null);
    }
}
