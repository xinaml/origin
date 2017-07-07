package com.bjike.act.shake;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.comment.CommentDTO;
import com.bjike.entity.user.User;
import com.bjike.ser.shake.IShakeSer;
import com.bjike.vo.comment.CommentVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-06 13:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
public class ShakeAct {
    /**
     * 摇一摇
     *
     * @return
     * @throws Exception
     */
    @Autowired
    private IShakeSer shakeSer;
    @GetMapping("/shake")
    public Result list(String pointX,String pointY,HttpServletRequest request) throws ActException {
        try {
            String userId = request.getHeader("userId");
             User vos = shakeSer.shake(userId,pointX,pointY);
            return ActResult.initialize(vos);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
