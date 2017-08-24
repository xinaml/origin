package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.dto.user.UserDTO;
import com.bjike.entity.user.User;
import com.bjike.ser.Ser;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 14:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserSer extends Ser<User, UserDTO> {
    /**
     * 是否已登录
     *
     * @param token
     * @return
     * @throws SerException
     */
    default Boolean isLogin(String token) throws SerException {
        return null;
    }

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     * @throws SerException
     */
    default User findByUsername(String username) throws SerException {
        return null;
    }

    /**
     * 通过手机号查询用户
     *
     * @param phone
     * @return
     * @throws SerException
     */
    default User findByPhone(String phone) throws SerException {
        return null;
    }

    /**
     * 找回密码
     * @param phone
     * @param password
     * @return
     * @throws SerException
     */
    default Boolean findPwd(String phone,String password) throws SerException {
        return null;
    }
    /**
     * 更改密码
     * @param userId
     * @param password
     * @return
     * @throws SerException
     */
    default Boolean editPwd(String userId,String password) throws SerException {
        return null;
    }

}
