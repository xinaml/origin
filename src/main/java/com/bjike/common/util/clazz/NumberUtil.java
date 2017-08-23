package com.bjike.common.util.clazz;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author: [liguiqin]
 * @Date: [2017-06-29 14:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NumberUtil {
    /**
     * 四舍五入
     * @param number
     * @return
     */
    public static double decimalUp(double number) {
        return Double.parseDouble(new DecimalFormat("#.00").format(number));
    }

    /**
     * 保留两位小数
     * @param number
     * @return
     */
    public static double decimal(double number) {
        BigDecimal bg = new BigDecimal(number);
        return bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }



}
