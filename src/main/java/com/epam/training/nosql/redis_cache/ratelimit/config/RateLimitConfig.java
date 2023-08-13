package com.epam.training.nosql.redis_cache.ratelimit.config;

import com.epam.training.nosql.redis_cache.ratelimit.model.RateLimitRule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "ratelimitRules")
public class RateLimitConfig {

    private List<RateLimitRule> rateLimitRules;

    @Bean
    public List<RateLimitRule> rateLimitRules() {
        return getRateLimitRules();
    }

    public List<RateLimitRule> getRateLimitRules() {
        return rateLimitRules;
    }

    public void setRateLimitRules(List<RateLimitRule> rateLimitRules) {
        this.rateLimitRules = rateLimitRules;
    }
}
