package com.farm.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @description: PropertiesUtil
 * @author: xyc
 * @create: 2021-02-12 10:46
 */
public class PropertiesUtil {
    private static Properties applicationProperties;

    //Tomcat运行时执行
    //代码块执行顺序：静态代码块>普通代码块>构造代码块
    //构造代码块每次都执行，但是静态代码块只执行一次
    static {
        try {
            applicationProperties = new Properties();
            applicationProperties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties"), "UTF-8"));
        } catch (IOException e) {
        }
    }

    //自定义俩个get方法，方便调用工具类读取properties文件的属性
    public static String getApplicationProperties(String key) {
        String value = applicationProperties.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value.trim();
    }

    public static String getApplicationProperties(String key, String defaultValue) {
        String value = applicationProperties.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }
        return value.trim();
    }

}