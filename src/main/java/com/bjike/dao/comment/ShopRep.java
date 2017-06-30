package com.bjike.dao.comment;

import com.bjike.dao.JpaRep;
import com.bjike.dto.comment.ShopDTO;
import com.bjike.entity.comment.Shop;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ShopRep extends JpaRep<Shop, ShopDTO> {

}
