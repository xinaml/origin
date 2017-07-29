package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.entity.user.User;
import com.bjike.ser.comment.LikesSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 14:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class UserImpl implements IUserSer {

    @Autowired
    private LikesSer likesSer;

    public User findById(String userId) throws SerException {
        String sql = "select a.tu_id ,b.avatar_image as headPath,a.nickname from ike_user a,ike_avatar b where" +
                " a.avatar_id=b.avatar_id and a.tu_id='" + userId + "'";
        List<User> users = likesSer.findBySql(sql, User.class, new String[]{"tu_id", "headPath", "nickname"});
        if (null != users && users.size() > 0) {
            return users.get(0);
        }
        throw new SerException("没找到用户");
    }
}
