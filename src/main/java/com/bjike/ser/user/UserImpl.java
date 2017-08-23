package com.bjike.ser.user;

import com.alibaba.fastjson.JSON;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.SerException;
import com.bjike.common.util.PasswordHash;
import com.bjike.dao.user.UserRep;
import com.bjike.dto.user.UserDTO;
import com.bjike.entity.user.User;
import com.bjike.redis.client.RedisClient;
import com.bjike.ser.ServiceImpl;
import com.bjike.session.UserSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 14:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class UserImpl extends ServiceImpl<User, UserDTO> implements UserSer {
    @Autowired
    private UserRep userRep;
    @Autowired
    private RedisClient redis;

    @Override
    public User findByUsername(String username) throws SerException {
        return userRep.findByUsername(username);
    }

    @Override
    public User currentUser(String token) throws SerException {
        return currentLoginUser(token);
    }

    @Override
    public User findByPhone(String phone) throws SerException {
        return userRep.findByPhone(phone);
    }

    @Override
    public Boolean isLogin(String token) throws SerException {
        return null != currentLoginUser(token);
    }

    @Override
    public Boolean findPwd(String phone, String password) throws SerException {
        try {
            User user = this.findByPhone(phone);
            if (null != user) {
                user.setPassword(PasswordHash.createHash(password));
                super.update(user);
            } else {
                throw new SerException("手机号不存在!");
            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        return true;
    }

    @Override
    public Boolean editPwd(String userId, String password) throws SerException {
        User user = super.findById(userId);
        if (null != user) {
            try {
                user.setPassword(PasswordHash.createHash(password));
            } catch (Exception e) {
                throw new SerException(e.getMessage());
            }
            super.update(user);
            return true;
        }
        return false;
    }

    private User currentLoginUser(String token) throws SerException {
        if (null != token) {
            User loginUser = UserSession.get(token);
            if (null != loginUser) {
                return loginUser;
            } else { //redis 获取
                String loginUser_str = redis.getMap(UserCommon.LOGIN_USER, token.toString());
                if (StringUtils.isNotBlank(loginUser_str)) {
                    loginUser = JSON.parseObject(loginUser_str, User.class);
                    UserSession.put(token.toString(), loginUser); //设置到session
                    return loginUser;
                }
            }
            throw new SerException("登录已失效!");
        } else {
            throw new SerException("用户未登录!");
        }
    }
}


