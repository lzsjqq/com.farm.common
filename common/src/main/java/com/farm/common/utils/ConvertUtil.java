package com.farm.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @description:
 * @author: xyc
 * @create: 2019-09-07 14:52
 */
public class ConvertUtil {

    public static <T> List<T> convert(List list,Class<T> clazz){
       return JSONObject.parseArray(JSONObject.toJSONString(list), clazz);
    }
}
