package com.epam.training.nosql.redis_cache.ratelimit.config;

import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;


@Configuration
public class RedisConfig {

    @Value("${redis.connection.url}")
    private String url;

    @Bean
    public Config config() {
        Config config = new Config();
        config.useSingleServer().setAddress(url);
        return config;
    }
}