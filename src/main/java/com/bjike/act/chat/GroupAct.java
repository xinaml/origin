package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.chat.IGroupSer;
import com.bjike.to.chat.GroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class GroupAct {

    @Autowired
    private IGroupSer groupSer;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(GroupTO to) throws ActException {
        try {
            groupSer.add(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable String id) throws ActException {
        try {
            groupSer.remove(id);
            return ActResult.initialize("delete sueecss");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.PUT)
    @ResponseBody
    public Result edit(GroupTO to) throws ActException{
        try {
            groupSer.edit(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
