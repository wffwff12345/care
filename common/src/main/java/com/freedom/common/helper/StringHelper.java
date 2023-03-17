package com.freedom.common.helper;

import java.util.Random;

public class StringHelper {

    /**
     * 判断对象是否为空
     *
     * @param value 字符串
     * @return true:为空 false:不为空
     */
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    /**
     * 获取随机长度的字符串
     * @param length 长度
     * @return 随机字符串
     */
    public static String random(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 可见字符 33 - 126
            sb.append((char) (random.nextInt(93) + 33));
        }
        return sb.toString();
    }

}
