package com.farm.common.utils;

import java.util.Collection;

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
     * @param strs
     * @return
     */
    public static boolean isNotBlank(String[] strs) {
        return strs != null && strs.length > 0;
    }


}