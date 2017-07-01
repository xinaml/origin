package com.bjike.dto.comment;

import com.bjike.dto.BaseDTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-27 16:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ShopDTO extends BaseDTO{
    private String pointX;
    private String pointY;

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
}
