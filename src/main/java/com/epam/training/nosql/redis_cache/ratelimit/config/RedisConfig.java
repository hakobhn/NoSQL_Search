package com.epam.training.nosql.redis_cache.ratelimit.config;

import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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