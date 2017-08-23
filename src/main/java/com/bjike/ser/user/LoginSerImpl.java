package com.bjike.ser.user;

import com.alibaba.fastjson.JSON;
import com.bjike.common.constant.UserCommon;
import com.bjike.common.exception.SerException;
import com.bjike.common.util.IpUtil;
import com.bjike.common.util.PasswordHash;
import com.bjike.common.util.TokenUtil;
import com.bjike.common.util.date.DateUtil;
import com.bjike.entity.user.User;
import com.bjike.redis.client.RedisClient;
import com.bjike.session.UserSession;
import com.bjike.to.user.LoginLogTO;
import com.bjike.to.user.LoginTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-22 10:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class LoginSerImpl implements LoginSer {
    @Autowired
    private UserSer userSer;
    @Autowired
    private LoginLogSer loginLogSer;
    @Autowired
    private RedisClient redis;

    @Override
    public String login(LoginTO to) throws SerException {
        String token = null;
        String account = to.getAccount();
        User user = userSer.findByPhone(account); //通过用户名/手机号/或者邮箱查找用户
        if (null != user) {
            token = validatePassword(to, user);  //验证密码
            if (StringUtils.isNotBlank(token)) { //登录成功处理业务
                saveLoginLog(to, user);  //记录登录日志
                saveToSessionAndRedis(user, token); //保存登录用户到session和redis
            } else {
                throw new SerException("账号或者密码错误");
            }
        } else {

            throw new SerException("账号或者密码错误");
        }
        return token;
    }

    @Override
    public Boolean logout(String token) throws SerException {
        UserSession.remove(token);
        return true;
    }

    /**
     * 验证登陆密码
     *
     * @param loginTO
     */
    private String validatePassword(LoginTO loginTO, User persistUser) throws SerException {
        String token = null;
        try {

            String password = loginTO.getPassword(); //不使用加密公钥
            if (PasswordHash.validatePassword(password, persistUser.getPassword())) {
                token = createToken(persistUser, loginTO);
            } else { //密码错误
                return null;
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        return token;

    }

    /**
     * 创建 token 令牌
     *
     * @param persistUser
     * @param loginTO
     * @return
     */
    private String createToken(User persistUser, LoginTO loginTO) throws SerException {
        String token = TokenUtil.create(loginTO.getIp());
        saveToSessionAndRedis(persistUser, token);
        return token;
    }

    /**
     * 保存登录用户到Session redis
     *
     * @param user
     * @param token
     * @throws SerException
     */
    private User saveToSessionAndRedis(User user, String token) throws SerException {
        UserSession.put(token, user);
        redis.appendToMap(UserCommon.LOGIN_USER, token, JSON.toJSONString(user), UserCommon.LOGIN_TIMEOUT);
        return user;
    }


    private void saveLoginLog(LoginTO loginTO, User user) throws SerException {
        LoginLogTO loginLogTO = new LoginLogTO();
        try {
            loginLogTO.setLoginIp(loginTO.getIp());
            loginLogTO.setLoginType(loginTO.getLoginType());
            loginLogTO.setUser(user);
            loginLogTO.setLoginTime(DateUtil.dateToString(LocalDateTime.now()));
            String address = IpUtil.getAddress(loginTO.getIp());
            loginLogTO.setLoginAddress(address);
            loginLogSer.saveLoginLog(loginLogTO);
        } catch (Exception e) {
            loginLogTO.setLoginAddress("not has address");
            loginLogSer.saveLoginLog(loginLogTO);
        }
    }

}
