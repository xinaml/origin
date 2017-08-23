package com.bjike.ser.user;

import com.bjike.common.exception.SerException;
import com.bjike.entity.user.LoginLog;
import com.bjike.to.user.LoginLogTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 14:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface LoginLogSer {
    /**
     * 保存登录日志
     * @param loginLogTO
     * @throws SerException
     */
    default void saveLoginLog(LoginLogTO loginLogTO) throws SerException {

    }

    /**
     * 获取用户登录日志，默认前10条（最多保存也是10条）
     *
     * @return
     */
    default List<LoginLog> findByUserId(String userId) throws SerException {
        return null;
    }
}
