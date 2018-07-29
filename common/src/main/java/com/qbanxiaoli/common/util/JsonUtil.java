package com.qbanxiaoli.common.util;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author qbanxiaoli
 * @description Josn字符串工具类
 * @create 2018/7/3 13:31
 */
public class JsonUtil {

    private JsonUtil(){
        throw new AssertionError("不能实例化JsonUtil");
    }

    public static byte[] toJsonBytes(Object o) throws Exception {
        // 使用ObjectMapper来转化对象为Json
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(o);
    }

    public static String toJsonString(Object o) throws Exception {
        // 使用ObjectMapper来转化对象为Json
        ObjectMapper mapper = new ObjectMapper();
        // 配置mapper忽略空属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 默认情况，Jackson使用Java属性字段名称作为 Json的属性名称,也可以使用Jackson annotations(注解)改变Json属性名称
        return mapper.writeValueAsString(o);
    }

}
