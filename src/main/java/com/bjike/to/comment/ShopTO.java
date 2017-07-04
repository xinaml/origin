package com.bjike.to.comment;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

public class ShopTO extends BaseTO {
    /**
     * 地址
     */
    @NotBlank(message = "请填写店铺地址", groups = {ADD.class, EDIT.class})

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
     * 范围(千米)
     */
    private Double range=0.5;
    /**
     * 店铺名
     */
    @NotBlank(message = "请填写店铺名", groups = {ADD.class, EDIT.class})

    private String name;



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

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }
}
