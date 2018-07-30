package com.qbanxiaoli.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author qbanxiaoli
 * @description MD5加密工具类
 * @create 2018/7/3 13:31
 */
public class MD5Util {

    private MD5Util(){
        throw new AssertionError("不能实例化MD5Util");
    }

    /**
     * @param source  待加密的字符串
     * @return 使用MD5加密后的字节数组
     * @author qbanxiaoli
     * @description 将源字符串通过MD5进行加密为字节数组
     */
    private static byte[] encodeToBytes(String source) {
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();//重置  
            md.update(source.getBytes("UTF-8"));//添加需要加密的源  
            result = md.digest();//加密
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param source  待加密的字符串
     * @return 使用MD5加密后的16进制字符串
     * @author qbanxiaoli
     * @description 将源字符串通过MD5加密成32位16进制数
     */
    private static String encodeToHex(String source) {
        byte[] data = encodeToBytes(source);//先加密为字节数组  
        StringBuilder hexSb = new StringBuilder();
        for (byte aData : data) {
            String hex = Integer.toHexString(0xff & aData);
            if (hex.length() == 1) {
                hexSb.append("0");
            }
            hexSb.append(hex);
        }
        return hexSb.toString();
    }

    /**
     * @param unknown 待验证的字符串
     * @param okHex   使用MD5加密后的16进制字符串
     * @return true or false
     * @author qbanxiaoli
     * @description 验证字符串是否匹配
     */
    public static boolean validate(String unknown, String okHex) {
        return okHex.equals(encodeToHex(unknown));
    }
}