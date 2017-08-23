package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.PasswordHash;
import com.bjike.common.util.regex.Validator;
import com.bjike.entity.user.User;
import com.bjike.session.AuthCodeSession;
import com.bjike.to.user.LoginTO;
import com.bjike.to.user.RegisterTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 10:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RegisterSerImpl implements RegisterSer {
    @Autowired
    private UserSer userSer;
    @Autowired
    private LoginSer loginSer;

    @Transactional
    @Override
    public String register(RegisterTO to) throws SerException {
        String token = null;
        if (true || to.getAuthCode().equalsIgnoreCase(AuthCodeSession.get(to.getSid()))) { //验证码
            if (to.getPassword().equals(to.getRePassword())) { //密码
                if (Validator.isPassword(to.getPassword())) { //密码强度
                    User user = new User();
                    user.setPhone(to.getPhone());
                    user.setUsername(to.getNickname());
                    user.setNickname(to.getNickname());
                    try {
                        user.setPassword(PasswordHash.createHash(to.getPassword()));
                    } catch (Exception e) {
                        throw new SerException(e.getMessage());
                    }
                    userSer.save(user);
                    token = loginUser(to);
                    return token;
                } else {
                    throw new SerException("密码过于简单！");
                }
            } else {
                throw new SerException("密码不一致!");
            }
        } else {
            throw new SerException("验证码错误!");
        }
    }

    @Override
    public void handleAuthCode(String sid, String code) throws SerException {
        AuthCodeSession.put(sid, code);
    }

    /**
     * 注册成功并登录用户
     *
     * @param to
     * @throws SerException
     */
    private String loginUser(RegisterTO to) throws SerException {
        LoginTO loginTO = new LoginTO();
        loginTO.setLoginType(to.getLoginType());
        loginTO.setAccount(to.getPhone());
        loginTO.setPassword(to.getPassword());
        loginTO.setIp(to.getIp());
        return loginSer.login(loginTO);
    }

}
