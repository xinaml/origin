package com.bjike.act.friend;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.ser.user.IFriendSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RequestMapping("friend")
@RestController
@LoginAuth
public class FriendAct {
    @Autowired
    private IFriendSer friendSer;

    @RequestMapping(value = {"/chain/{name}"})
    public Result search(@PathVariable String name, HttpServletRequest request) throws ActException {
        try {
            String userId = request.getHeader("userId");
            return ActResult.initialize(friendSer.search(name, userId));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
