package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.dto.user.RecommendDTO;
import com.bjike.entity.user.Recommend;
import com.bjike.ser.ServiceImpl;
import com.bjike.to.user.RecommendTO;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecommendSerImpl extends ServiceImpl<Recommend, RecommendDTO> implements RecommendSer {
    @Override
    public void add(RecommendTO to) throws SerException {

    }
}
