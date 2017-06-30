package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Shop;
import com.bjike.ser.Ser;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface IShopSer extends Ser<Shop, ShopDTO> {
    default Shop add(Shop shop) throws SerException {
        return null;
    }
}
