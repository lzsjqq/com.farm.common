package com.farm.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 日志打印工具类
 * @author: xyc
 * @create: 2021-02-11 11:07
 */
public class MagicalLogUtil {
    public static Logger log = LoggerFactory.getLogger(MagicalLogUtil.class);

    /**
     * 打印警告
     *
     * @param format format
     * @param obj    obj
     */
    public static void warn(String format, Object... obj) {
        //获取输出信息的代码的位置
        log.warn(getLogPreMessage() + format, obj);
    }

    /**
     * 打印警告
     *
     * @param msg msg
     */
    public static void warn(String msg) {
        //获取输出信息的代码的位置
        log.warn(getLogPreMessage() + msg);
    }

    /**
     * info
     *
     * @param format format
     * @param obj    obj
     */
    public static void info(String format, Object... obj) {
        //获取输出信息的代码的位置
        log.info(getLogPreMessage() + format, obj);
    }

    /**
     * info
     *
     * @param msg msg
     */
    public static void info(String msg) {
        //获取输出信息的代码的位置
        log.info(getLogPreMessage() + msg);
    }

    /**
     * debug
     *
     * @param format format
     * @param obj    obj
     */
    public static void debug(String format, Object... obj) {
        //获取输出信息的代码的位置
        log.debug(getLogPreMessage() + format, obj);
    }

    /**
     * debug
     *
     * @param msg msg
     */
    public static void debug(String msg) {
        //获取输出信息的代码的位置
        log.debug(getLogPreMessage() + msg);
    }

    /**
     * error
     *
     * @param format format
     * @param obj    obj
     */
    public static void error(String format, Object... obj) {
        //获取输出信息的代码的位置
        log.error(getLogPreMessage() + format, obj);
    }

    /**
     * error
     *
     * @param msg msg
     */
    public static void error(String msg) {
        //获取输出信息的代码的位置
        log.error(getLogPreMessage() + msg);
    }


    private static String getLogPreMessage() {
        String location;
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        location = stacks[2].getClassName() + "." + stacks[2].getMethodName() + "(" + stacks[2].getLineNumber() + ")";
        return location;
    }

}