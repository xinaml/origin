package com.bjike.act.user;

import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.util.PasswordHash;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.entity.user.User;
import com.bjike.ser.user.UserSer;
import com.bjike.vo.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 15:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("user")
public class UserAct {
    @Autowired
    private UserSer userSer;

    /**
     * 当前用户信息
     *
     * @return
     * @throws ActException
     */
    @LoginAuth
    @GetMapping("/info")
    public ActResult userInfo() throws ActException {
        try {
            User user =UserUtil.currentUser();
            return ActResult.initialize(BeanCopy.copyProperties(user, UserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 找回密码
     *
     * @param phone
     * @return
     * @throws ActException
     */
    @PutMapping("/findPwd/phone")
    public ActResult findPwd(@PathVariable String phone, String password, String rePassword) throws ActException {
        try {
            if (password.equals(rePassword)) {
                userSer.findPwd(phone, password);
            } else {
                throw new ActException("密码不一致!");
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult("success");
    }

    /**
     * 找回密码
     *
     * @param oldPassword
     * @param password
     * @param rePassword
     * @return
     * @throws ActException
     */
    @LoginAuth
    @PutMapping("/editPwd")
    public ActResult editPwd(String oldPassword, String password, String rePassword) throws ActException {
        try {
            if (password.equals(rePassword)) {
                boolean pass = false;
                User user =UserUtil.currentUser();
                try {
                    pass = PasswordHash.validatePassword(oldPassword, user.getPassword());
                } catch (Exception e) {
                    throw new ActException("密码解析验证错误!");
                }
                if (pass) {
                    userSer.editPwd(user.getId(), password);
                } else {
                    throw new SerException("旧密码输入错误");
                }

            } else {
                throw new ActException("密码不一致!");
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult("success");
    }

}
