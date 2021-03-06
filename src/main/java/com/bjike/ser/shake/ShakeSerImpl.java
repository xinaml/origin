package com.bjike.ser.shake;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.entity.user.User;
import com.bjike.ser.user.UserSer;
import com.bjike.session.ShareSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 13:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ShakeSerImpl implements ShakeSer {

    @Autowired
    private UserSer userSer;

    @Override
    public User shake( String pointX, String pointY) throws SerException {
        String userId = UserUtil.currentUserID();
        String now = StringUtils.substring(LocalDateTime.now().toString(), 0, -2);
        User user = ShareSession.get(now);
        if (null == user) {
            User freshUser = userSer.findById(userId);
            freshUser.setPointX(pointX);
            freshUser.setPointY(pointY);
            ShareSession.put(now, freshUser);
            int count = 0;
            while (count < 9 && user == null) {
                String newNow = StringUtils.substring(now, 0, -1) + (count++);
                freshUser = ShareSession.get(newNow);
                if (null != freshUser && !freshUser.getId().equals(userId)) {
                    ShareSession.remove(newNow);
                    freshUser.setPointX(pointX);
                    freshUser.setPointY(pointY);
                    ShareSession.put(newNow, user);
                    user = freshUser;
                    now = newNow;
                }
            }
        }
        if (null != user) {
            ShareSession.remove(now);
            return user;
        }
        return null;
    }

}
