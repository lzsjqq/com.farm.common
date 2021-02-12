package com.farm.common.redis;

import com.up.strawberry.common.utils.PropertiesUtil;
import com.up.strawberry.common.utils.log.MagicalLogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @description: redis 配置类
 * @author: xyc
 * @create: 2021-02-12 10:06
 */
@Component
public class RedisConfig {

    @Autowired
    private RedisBean redisBean;

    /**
     * 集群实例
     *
     * @return JedisConnectionFactory
     */
    public JedisConnectionFactory clusterConnectionFactory() {
        Collection<RedisNode> redisNodes = new ArrayList<>();
        JedisConnectionFactory jedisConnectionFactory ;
        String host = redisBean.getHost();
        try {
            if (null == host || "".equals(host)) {
                // 没有redis节点信息。
                return null;
            }
            for (String hostAndPort : host.split(",")) {
                String[] detail = hostAndPort.split(":");
                redisNodes.add(new RedisNode(detail[0], Integer.parseInt(detail[1])));
            }
            //配置redis集群信息
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            redisClusterConfiguration.setClusterNodes(redisNodes);
            redisClusterConfiguration.setMaxRedirects(3);
            redisClusterConfiguration.setPassword(redisBean.getPassword());
            JedisPoolConfig jedisPoolConfig = getJedisPoolConfig();
            //集群配置信息、jedis链接池配置信息 来 实例化jedis链接池
            jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        } catch (Exception e) {
            return null;
        }
        return jedisConnectionFactory;

    }

    private JedisPoolConfig getJedisPoolConfig() {
        //配置jedis链接池信息
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接数，默认为8个
        jedisPoolConfig.setMaxTotal(redisBean.getPoolMaxActive());
        // 最大空闲连接数，默认为8个,控制一个pool最多有多少个状态为idle的jedis实例
        jedisPoolConfig.setMaxIdle(redisBean.getPoolMaxIdle());
        jedisPoolConfig.setMinIdle(redisBean.getPoolMinIdle());

        // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException;
        jedisPoolConfig.setMaxWaitMillis(redisBean.getPoolMaxWait());

        // 连接耗尽时否阻塞，false报异常，true阻塞直到超时，默认为true
        jedisPoolConfig.setBlockWhenExhausted(true);
        // 设置的逐出策略类名，默认DefaultEvictionPolicy(当连接超过最大空闲时间，或连接数据超过最大空闲连接数)
        jedisPoolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        // 是否启用Pool的JMX管理功能，默认为true
        jedisPoolConfig.setJmxEnabled(true);
        // 在borrow一个jedis实例时，是否提前进行validate操作，如果为true，则得到的jedis实例均是可用的；
        // 在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    /**
     * 单机实例
     *
     * @return JedisConnectionFactory
     */
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisBean.getHost());
        redisStandaloneConfiguration.setPort(redisBean.getPort());
        redisStandaloneConfiguration.setDatabase(redisBean.getDatabase());
        //由于我们使用了动态配置库,所以此处省略
        //redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(redisBean.getPassword());
        JedisClientConfiguration.JedisClientConfigurationBuilder clientConfiguration = JedisClientConfiguration.builder();
        clientConfiguration.connectTimeout(Duration.ofMillis(redisBean.getTimeout()));
        return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration.build());
    }

    public RedisTemplate functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        MagicalLogUtil.info("RedisTemplate实例化成功！");
        RedisTemplate redisTemplate = new RedisTemplate();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    private void initDomainRedisTemplate(RedisTemplate redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<>(Object.class);
    }


    public RedisTemplate obtainRedisTemplate() {
        RedisTemplate redisTemplate;
        String active = PropertiesUtil.getApplicationProperties("spring.profiles.active");
        if ("dev".equals(active)) {
            redisTemplate = functionDomainRedisTemplate(jedisConnectionFactory());
        } else {
            redisTemplate = functionDomainRedisTemplate(jedisConnectionFactory());
        }
        return redisTemplate;
    }
}