package com.farm.common.utils;

import java.util.UUID;

/**
 * @description: UUIDUtil
 * @author: xyc
 * @create: 2021-02-12 20:02
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}