package com.farm.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description: 对象转换
 * @author: xyc
 * @create: 2019-09-07 14:52
 */
public class ConvertUtil {
    /**
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> convert(List list, Class<T> clazz) {
        return JSONObject.parseArray(JSONObject.toJSONString(list), clazz);
    }

    /**
     * @param obj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convert(Object obj, Class<T> clazz) {
        return JSONObject.parseObject(JSONObject.toJSONString(obj), clazz);
    }
}
