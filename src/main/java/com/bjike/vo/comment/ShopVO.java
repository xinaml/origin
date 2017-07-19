package com.bjike.vo.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import com.bjike.vo.BaseVO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class ShopVO extends BaseVO {
    /**
     * 地址
     */

    private String address;
    /**
     * x坐标
     */

    private String pointX;
    /**
     * y坐标
     */

    private String pointY;

    /**
     * 店铺名
     */

    private String name;

    /**
     * 店铺用户评价top3
     */
    private String images[];

    private String pointId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPointX() {
        return pointX;
    }

    public void setPointX(String pointX) {
        this.pointX = pointX;
    }

    public String getPointY() {
        return pointY;
    }

    public void setPointY(String pointY) {
        this.pointY = pointY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
}
