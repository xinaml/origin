package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.entity.user.User;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 14:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IUserSer {
    default User findById(String userId)throws SerException {
        return null;
    }
}
