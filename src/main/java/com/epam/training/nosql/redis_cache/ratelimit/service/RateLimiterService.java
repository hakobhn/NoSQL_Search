package com.epam.training.nosql.redis_cache.ratelimit.service;

import com.epam.training.nosql.redis_cache.ratelimit.model.BusinessRequest;
import io.github.bucket4j.Bucket;

public interface RateLimiterService {
    Bucket resolveBucket(BusinessRequest businessRequest);
}
