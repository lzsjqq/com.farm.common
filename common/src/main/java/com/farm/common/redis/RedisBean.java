package com.farm.common.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: redis配置类
 * @author: xyc
 * @create: 2021-02-12 08:33
 */
@Component
@PropertySource("classpath:business/${spring.profiles.active}/redis.properties")
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisBean {


    /**
     * Redis数据库索引（默认为0）
     */
    private int database;
    /**
     * Redis服务器地址
     */
    private String host;
    /**
     * Redis服务器连接端口
     */
    private Integer port;
    /**
     * Redis服务器连接密码（默认为空）
     */
    private String password;
    /**
     * 连接池最大连接数（使用负值表示没有限制）
     */
    private int poolMaxActive;
    /**
     * 连接池最大阻塞等待时间（使用负值表示没有限制）
     */
    private long poolMaxWait;
    /**
     * 连接池中的最大空闲连接
     */
    private int poolMaxIdle;
    /**
     * 连接池中的最小空闲连接
     */
    private int poolMinIdle;
    /**
     * 连接超时时间（毫秒）
     */
    private int timeout;
    /**
     * 连接池最大链接数默认值为8
     */
    private String lettucePoolMaxActive;
    /**
     * 读取数据超时
     */
    private int soTimeout;
}