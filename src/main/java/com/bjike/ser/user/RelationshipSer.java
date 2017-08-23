package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.dto.user.RelationshipDTO;
import com.bjike.entity.user.Relationship;
import com.bjike.entity.user.User;
import com.bjike.ser.Ser;
import com.bjike.ser.ServiceImpl;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-10 14:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RelationshipSer extends Ser<Relationship,RelationshipDTO> {
    /**
     * 关系链
     * @param name
     * @param token
     * @return
     * @throws SerException
     */
    default Relationship search(String name, String token)throws SerException{
        return null;
    }
}
