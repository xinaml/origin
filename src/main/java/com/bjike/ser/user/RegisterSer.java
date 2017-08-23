package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.to.user.RegisterTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 10:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RegisterSer {
    /**
     * 注册
     * @param to
     * @return
     * @throws SerException
     */
    default String register(RegisterTO to) throws SerException {
        return null;
    }
    /**
     * 保存生成的注册验证码
     * @param sid
     * @param code
     * @return
     * @throws SerException
     */
    default void  handleAuthCode(String sid,String code) throws SerException {
    }

}
