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
    private Double pointX;
    private Double pointY;
    /**
     * 范围(千米)
     */
    private Double range=0.5;



    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Double getPointX() {
        return pointX;
    }

    public void setPointX(Double pointX) {
        this.pointX = pointX;
    }

    public Double getPointY() {
        return pointY;
    }

    public void setPointY(Double pointY) {
        this.pointY = pointY;
    }

}
