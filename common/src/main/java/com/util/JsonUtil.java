package com.util;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Josn字符串工具类
 * @author qbanxiaoli
 * @time 2018年4月17日
 *
 */
public class JsonUtil {

    private JsonUtil(){
        throw new AssertionError("不能实例化 JsonUtil");
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


    /**
     * 将json字符串中带有下划线的key转成驼峰命名形式的key
     * @param jsonStr
     * @return
     */
    public static String jsonKeyToCamelCase(String jsonStr){

        //转换后的字符串
        String str = jsonStr;

        //满足json字符串key的正则
        String regx = "\"\\w+\":";

        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);

        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(jsonStr);

        //查找符合规则的子串
        while(matcher.find()){
            System.out.println(matcher.group());
            str = str.replaceFirst(matcher.group(),camelCase(matcher.group()));
        }

        return str;
    }
    public static String deleteBlankString(String jsonStr){
        //去除可能存在的    "******":"   ",     形式的空白字符串
        String regx = "\"\\w+\":\" *\",";
        jsonStr = deleteBlankString(regx,jsonStr);
        //去除可能存在的    "******":"   "      形式的空白字符串
        regx = "\"\\w+\":\" *\"";
        jsonStr = deleteBlankString(regx,jsonStr);
        //将可能存在的  ,}  替换成  }
        jsonStr = jsonStr.replaceFirst(",}","}");
        return  jsonStr;
    }
    private static String deleteBlankString(String regx , String jsonStr){
        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);

        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(jsonStr);

        //查找符合规则的子串
        while(matcher.find()){
            jsonStr = jsonStr.replaceFirst(matcher.group(),"");
        }
        return jsonStr;
    }

    private static String camelCase(String str){
        String camelCase = "";
        String [] arr = str.split("_");
        List<String> list = new ArrayList<String>();

        //将数组中非空字符串添加至list
        for(String a : arr){
            if(a.length() > 0){
                list.add(a);
            }
        }

        for(int i=0;i<list.size();i++){
            if(i>0){    //后面单词首字母大写
                char c = list.get(i).charAt(0);
                String s = String.valueOf(c).toUpperCase() + list.get(i).substring(1).toLowerCase();
                camelCase+=s;
            }else{  //首个单词小写
                camelCase+=list.get(i).toLowerCase();
            }
        }
        return camelCase;
    }




    /**
     * 将json字符串中的数值类型转为字符类型
     * 例如{"page":5}转为{"page":"5"}
     * @param jsonStr
     * @return
     */
    public static String jsonNumberToString(String jsonStr){

        //转换后的字符串
        String str = jsonStr;

        //满足json字符串key的正则
        String regx = ":\\d+([.]{1}[0-9]+){0,1}";

        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);

        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(jsonStr);

        //查找符合规则的子串
        while(matcher.find()){
            str = str.replaceFirst(matcher.group(),":\""+matcher.group().replace(":","")+"\"");
        }

        return str;
    }

}
