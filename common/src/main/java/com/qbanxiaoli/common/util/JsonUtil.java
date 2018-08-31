package com.qbanxiaoli.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qbanxiaoli
 * @description Json字符串工具类
 * @create 2018/7/3 13:31
 */
@Slf4j
public class JsonUtil {

    private JsonUtil() {
        throw new AssertionError("不能实例化JsonUtil");
    }

    /**
     * @param object 待转化的对象
     * @return Byte数组
     * @author qbanxiaoli
     * @description 使用ObjectMapper来转化对象为Byte数组
     */
    public static byte[] toJsonBytes(Object object) throws Exception {
        // 使用ObjectMapper来转化对象为Byte数组
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置objectMapper忽略空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsBytes(object);
    }

    /**
     * @param object 待转化的对象
     * @return Json字符串
     * @author qbanxiaoli
     * @description 使用ObjectMapper来转化对象为Json
     */
    public static String toJsonString(Object object) throws Exception {
        // 使用ObjectMapper来转化对象为Json
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置objectMapper忽略空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsString(object);
    }

}
