package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.entity.user.Relationship;
import com.bjike.entity.user.User;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-10 14:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IRelationshipSer {
    default Relationship search(String name, String userId)throws SerException{
        return null;
    }
}
