package com.farm.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @description: 是否为空值校验
 * @author: xyc
 * @create: 2019-08-24 22:52
 */
public class NullCheckUtils {
    /**
     * 集合校验
     *
     * @param collection
     * @return
     */
    public static boolean isNotBlank(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 集合校验
     *
     * @param collection
     * @return
     */
    public static boolean isBlank(Collection collection) {
        return !isNotBlank(collection);
    }

    /**
     * map校验
     *
     * @param map
     * @return
     */
    public static boolean isNotBlank(Map map) {
        return map != null && !map.isEmpty();
    }

    /**
     * map校验
     *
     * @param map
     * @return
     */
    public static boolean isBlank(Map map) {
        return !isNotBlank(map);
    }

    /**
     * String
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * String
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return !isNotBlank(str);
    }

    /**
     * String
     *
     * @param strs
     * @return
     */
    public static boolean isNotBlank(String[] strs) {
        return strs != null && strs.length > 0;
    }

    /**
     * Object
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        return null == obj;
    }

    /**
     * Object
     *
     * @param obj
     * @return
     */
    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }


}
