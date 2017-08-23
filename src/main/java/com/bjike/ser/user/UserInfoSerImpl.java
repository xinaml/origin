package com.bjike.ser.user;

import com.bjike.dto.user.UserInfoDTO;
import com.bjike.entity.user.UserInfo;
import com.bjike.ser.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 09:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class UserInfoSerImpl extends ServiceImpl<UserInfo,UserInfoDTO> implements  UserInfoSer {
}
