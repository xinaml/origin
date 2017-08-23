package com.bjike.common.util.clazz;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: [liguiqin]
 * @Date: [2017-07-04 17:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

/**
 * 是否为中文
 *
 */
public class StringUtil {
    private static final String REG = "[\u4e00-\u9fa5]";

    public static boolean isChinese(String str) {
        if(StringUtils.isNotBlank(str)){
            Pattern p = Pattern.compile(REG);
            Matcher m = p.matcher(str);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }


}
