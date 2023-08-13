package com.epam.training.nosql.redis_cache.ratelimit.service.impl;

import com.epam.training.nosql.redis_cache.ratelimit.model.BusinessRequest;
import com.epam.training.nosql.redis_cache.ratelimit.model.RateLimitRule;
import com.epam.training.nosql.redis_cache.ratelimit.service.RateLimiterService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterServiceImpl implements RateLimiterService {

    private final Map<BusinessRequest, Bucket> cache = new ConcurrentHashMap<>();
    private final List<RateLimitRule> rateLimitRules;


    public RateLimiterServiceImpl(List<RateLimitRule> rateLimitRules) {
        this.rateLimitRules = rateLimitRules;
    }

    public Bucket resolveBucket(BusinessRequest key) {
        Bucket bucket = rateLimitRules.stream()
                .filter(rule -> rule.getAccountId().equals(key.getAccountId()))
                .findFirst()
                .map(rule -> cache.computeIfAbsent(key, e -> newBucket(rule)))
                .orElseGet(
                        () -> rateLimitRules.stream()
                                .filter(rule -> rule.getClientIp().equals(key.getClientIp()))
                                .findFirst()
                                .map(rule -> cache.computeIfAbsent(key, e -> newBucket(rule)))
                                .orElse(null)
                );

        if (bucket == null) {

        }

        rateLimitRules.stream()
                .filter(rule -> rule.getClientIp().equals(key.getClientIp()))
                .map(
                        rule -> cache.computeIfAbsent(key, r -> newBucket(rule))
                );

        return null;
//        if (!rule.isPresent()) {
//            rule =  rateLimitRules.stream()
//                    .filter(r -> r.getAccountId().equals(key.getAccountId()))
//        }
//
//        rule
//                .map(
//                        r -> cache.computeIfAbsent(key, r -> newBucket(rule))
//                ).orElse(null);
    }

    private Bucket newBucket(RateLimitRule rule) {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(rule.getAllowedNumberOfRequests(),
                        Refill.intervally(rule.getAllowedNumberOfRequests(),
                                Duration.of(1, rule.getTimeInterval().toChronoUnit()))))
                .build();
    }
}
