package com.bjike.entity.comment;

import com.bjike.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-28 14:01]
 * @Description: [ 店铺]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "shop")
public class Shop extends BaseEntity{
    /**
     * 地址
     */
    @Column(columnDefinition = "VARCHAR(100) COMMENT '地址' ", nullable = false)

    private String address;
    /**
     * x坐标
     */
    @Column(columnDefinition = " DECIMAL(5,2) COMMENT 'x坐标' ", nullable = false)

    private Double pointX;
    /**
     * y坐标
     */
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT 'y坐标' ", nullable = false)

    private Double pointY;


    /**
     * 店铺名
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '店铺名' ", nullable = false)
    private String name;

    /**
     * 排序序列
     */
    @Column(columnDefinition = "TINYINT COMMENT '店铺名' ", nullable = false)
    private Integer seq;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
