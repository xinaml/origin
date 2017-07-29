package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Shop;
import com.bjike.ser.Ser;
import com.bjike.vo.comment.ShopVO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ShopSer extends Ser<Shop, ShopDTO> {
    /**
     * 添加店铺
     *
     * @param shop
     * @return
     * @throws SerException
     */
    default Shop add(Shop shop) throws SerException {
        return null;
    }

    /**
     * 附近店铺
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ShopVO> nearby(ShopDTO dto) throws SerException {
        return null;
    }
}
