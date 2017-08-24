package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.common.util.UserUtil;
import com.bjike.common.util.bean.BeanCopy;
import com.bjike.dto.user.RecommendDTO;
import com.bjike.entity.user.Recommend;
import com.bjike.entity.user.User;
import com.bjike.redis.client.RedisClient;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.user.RecommendTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecommendSerImpl extends ServiceImpl<Recommend, RecommendDTO> implements RecommendSer {
    @Autowired
    private RedisClient redis;

    @Override
    public String add(RecommendTO to) throws SerException {
        Recommend recommend = BeanCopy.copyProperties(to, Recommend.class);
        User user = UserUtil.currentUser();
        recommend.setUser(user);
        super.save(recommend);
        String code = "IKE-" + UUID.randomUUID().toString().substring(0, 13);
        redis.save(code, LocalDateTime.now().toString(), 30 * 60 * 60 * 24);
        return code;

    }

    @Override
    public Boolean validate(String code) throws SerException {
        boolean exist = (null != redis.get(code));
        redis.remove(code);
        return exist;
    }
}
