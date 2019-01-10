package com.util;

import java.util.Random;

/**
 * @author Q版小李
 * @description
 * @create 2018/8/9 17:40
 */
public class RandomUtil {

    private RandomUtil() {
        throw new AssertionError("不能实例化RandomUtil");
    }

    private static final String SOURCE = "0123456789";

    /**
     * @param length 随机字符串长度
     * @return 随机字符串
     * @author qbanxiaoli
     * @description 返回一个定长的随机字符串
     */
    public static String generateString(int length) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < length; j++) {
            sb.append(SOURCE.charAt(rand.nextInt(SOURCE.length())));
        }
        return sb.toString();
    }

}