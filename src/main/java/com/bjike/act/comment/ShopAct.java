package com.bjike.act.comment;

import com.bjike.common.exception.ActException;
import com.bjike.common.exception.SerException;
import com.bjike.common.interceptor.login.LoginAuth;
import com.bjike.common.restful.ActResult;
import com.bjike.common.restful.Result;
import com.bjike.dto.Restrict;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.ser.comment.IShopSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:07]
 * @Description: [店铺 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth //登录验证注解,header必须携带token
@RestController
@RequestMapping("shop")
public class ShopAct {
    @Autowired
    private IShopSer shopSer;

    /**
     * 附近店铺
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @GetMapping("/nearby")
    public Result nearby(ShopDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.lt("pointX", new String[]{dto.getPointX(), dto.getPointX()}));
            dto.getConditions().add(Restrict.eq("pointY", new String[]{dto.getPointY(), dto.getPointY()}));
            return ActResult.initialize(shopSer.findByCis(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 店铺删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable String id) throws ActException {
        try {
            shopSer.remove(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
