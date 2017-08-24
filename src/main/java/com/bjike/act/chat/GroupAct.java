package com.bjike.act.chat;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.common.util.UserUtil;
import com.bjike.entity.chat.Friend;
import com.bjike.ser.chat.FriendSer;
import com.bjike.ser.chat.GroupSer;
import com.bjike.to.chat.GroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    private GroupSer groupSer;

    @Autowired
    private FriendSer friendSer;
    /**
     * 群成员
     *
     * @param groupId
     * @return
     * @throws ActException
     */
    @RequestMapping(value = "member/{groupId}", method = RequestMethod.GET)
    @ResponseBody
    public Result groupMember(@PathVariable String groupId) throws ActException {
        try {
            return ActResult.initialize(friendSer.groupMember(groupId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @RequestMapping(value = "user/list", method = RequestMethod.GET)
    @ResponseBody
    public Result listByUser() throws ActException {
        try {
            String userId = UserUtil.currentUserID();
            groupSer.listByUser(userId);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(GroupTO to) throws ActException {
        try {
            groupSer.add(to);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable String id) throws ActException {
        try {
            groupSer.remove(id);
            return new ActResult("delete sueecss");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.PUT)
    @ResponseBody
    public Result edit(GroupTO to) throws ActException {
        try {
            groupSer.edit(to);
            return new ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
