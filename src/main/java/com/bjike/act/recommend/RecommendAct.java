package com.bjike.act.recommend;

import com.bjike.common.exception.ActException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.ser.user.RecommendSer;
import com.bjike.to.user.RecommendTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 推荐
 *
 * @Author: [liguiqin]
 * @Date: [2017-08-23 17:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping(name = "recommend")
public class RecommendAct {
    @Autowired
    private RecommendSer recommendSer;
    /**
     * 添加推荐并生成推荐码
     *
     * @return
     * @throws ActException
     */
    @LoginAuth
    @PostMapping("/add")
    public ActResult add(RecommendTO to) throws ActException {
        return new ActResult("success");
    }

    /**
     * 通过推荐码获取二维码
     *
     * @param code
     * @return
     * @throws ActException
     */
    @LoginAuth
    @PostMapping("/QRcode/{code}")
    public ActResult QRcode(@PathVariable String code) throws ActException {
        return new ActResult("success");
    }
}
