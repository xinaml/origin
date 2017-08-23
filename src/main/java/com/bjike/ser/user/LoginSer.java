package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.to.user.LoginTO;
import com.bjike.to.user.RegisterTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 10:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface LoginSer {
    /**
     * 登录
     * @param to
     * @return
     * @throws SerException
     */
    default String login(LoginTO to) throws SerException {
        return null;
    }

    /**
     * 注销
     * @param token
     * @return
     * @throws SerException
     */
    default Boolean logout(String token) throws SerException {
        return null;
    }
}
