package com.bjike.dao.user;

import com.bjike.dao.JpaRep;
import com.bjike.dto.user.UserDTO;
import com.bjike.entity.user.User;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 09:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserRep extends JpaRep<User, UserDTO> {
    User findByUsername(String username);

    User findByPhone(String phone);
}
