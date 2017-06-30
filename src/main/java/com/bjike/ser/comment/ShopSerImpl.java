package com.bjike.ser.comment;

import com.bjike.common.exception.SerException;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Shop;
import com.bjike.ser.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 08:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ShopSerImpl extends ServiceImpl<Shop, ShopDTO> implements IShopSer {
    @Transactional
    @Override
    public Shop add(Shop shop) throws SerException {
        Long seq = super.count(new ShopDTO());
        shop.setSeq(seq==null?0:seq.intValue() + 1);
        super.save(shop);
        return shop;
    }
}
