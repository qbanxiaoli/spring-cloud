package com.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author qbanxiaoli
 * @description MD5加密工具类
 * @create 2018/7/3 13:31
 */
@Slf4j
public class MD5Util {

    private MD5Util() {
        throw new AssertionError("不能实例化MD5Util");
    }

    /**
     * @param password 待加密的字符串
     * @return 使用MD5加密后的字节数组
     * @author qbanxiaoli
     * @description 将源字符串通过MD5进行加密为字节数组
     */
    public static String getStrMD5(String password) {
        // 获取MD5实例
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
        // 将加密字符串转换为字符数组
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        // 开始加密
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] digest = md5.digest(byteArray);
        StringBuilder sb = new StringBuilder();
        for (byte aDigest : digest) {
            int var = aDigest & 0xff;
            if (var < 16)
                sb.append("0");
            sb.append(Integer.toHexString(var));
        }
        return sb.toString();
    }

    /**
     * @param password 待加密的字符串
     * @return 使用MD5加盐密后的16进制字符串
     * @author qbanxiaoli
     * @description 将源字符串通过MD5加盐加密成字节数组
     */
    public static String getSaltMD5(String password) {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String salt = sBuilder.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    /**
     * @param password 待验证的字符串
     * @param md5str   使用MD5加盐加密后的16进制字符串
     * @return true or false
     * @author qbanxiaoli
     * @description 验证字符串是否匹配
     */
    public static boolean getSaltverifyMD5(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String Salt = new String(cs2);
        return md5Hex(password + Salt).equals(String.valueOf(cs1));
    }

    /**
     * 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
     */
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return new String(new Hex().encode(digest));
        } catch (Exception e) {
            log.error("字符串转换为字节数组失败：" + e);
            return "";
        }
    }

    /**
     * MD5双重解密
     */
    public static String getconvertMD5(String inStr) {
        char[] charArray = inStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (charArray[i] ^ 't');
        }
        return String.valueOf(charArray);
    }

}