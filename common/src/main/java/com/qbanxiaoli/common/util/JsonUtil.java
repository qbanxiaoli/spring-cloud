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

    public static byte[] toJsonBytes(Object o) throws Exception {
        // 使用ObjectMapper来转化对象为Json
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置objectMapper忽略空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper.writeValueAsBytes(o);

    }

    public static String toJsonString(Object o) throws Exception {
        // 使用ObjectMapper来转化对象为Json
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置objectMapper忽略空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 默认情况，Jackson使用Java属性字段名称作为 Json的属性名称,也可以使用Jackson annotations(注解)改变Json属性名称
        return objectMapper.writeValueAsString(o);
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
