package com.epam.training.nosql.redis_cache.ratelimit.service;

import com.epam.training.nosql.redis_cache.ratelimit.model.BusinessRequest;
import io.github.bucket4j.Bucket;

import java.util.List;

public interface RateLimiterService {
    Bucket resolveBucket(List<BusinessRequest> businessRequest);
}
