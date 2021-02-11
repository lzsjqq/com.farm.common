package com.farm.common.log;

/**
 * @description: 当前线程操作类
 * @author: xyc
 * @create: 2021-02-11 11:29
 */
public class CurrentThreadUtil {
    private static final String PRE = "T-";

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static String generateId() {
        return PRE + System.currentTimeMillis();
    }

    public static void setCurrentThreadId() {
        Thread.currentThread().setName(generateId());
    }

    public static void getCurrentThreadId() {
        threadLocal.get();
    }

    public static void releaseCurrentThreadId() {
        threadLocal.remove();
    }


}