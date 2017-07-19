package com.bjike.ser.shake;

import com.bjike.common.exception.SerException;
import com.bjike.entity.user.User;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IShakeSer {
    /**
     * 摇一摇
     *
     * @param userId
     * @param pointX
     * @param pointY
     * @return
     * @throws SerException
     */
    default User shake(String userId, String pointX, String pointY) throws SerException {
        return null;
    }
}