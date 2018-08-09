package com.qbanxiaoli.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qbanxiaoli
 * @description Josn字符串工具类
 * @create 2018/7/3 13:31
 */
@Slf4j
public class JsonUtil {

    private JsonUtil() {
        throw new AssertionError("不能实例化JsonUtil");
    }

    public static byte[] toJsonBytes(Object o) {
        // 使用ObjectMapper来转化对象为Byte数组
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置objectMapper忽略空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsBytes(o);
        } catch (Exception e) {
            log.error("序列化对象为Byte数组失败：" + e);
        }
        return null;
    }

    public static String toJsonString(Object o) {
        // 使用ObjectMapper来转化对象为Json
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置objectMapper忽略空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            log.error("序列化对象为Json失败：" + e);
        }
        return o.toString();
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        // 使用ObjectMapper来转化Json为对象
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            log.error("反序列化Json为对象失败:", e);
        }
        return null;
    }

}
