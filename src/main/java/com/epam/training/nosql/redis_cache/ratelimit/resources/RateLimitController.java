package com.epam.training.nosql.redis_cache.ratelimit.resources;

import com.epam.training.nosql.redis_cache.ratelimit.model.BusinessRequest;
import com.epam.training.nosql.redis_cache.ratelimit.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RateLimitController {

    private final RateLimiterService rateLimiterService;

    @PostMapping("/")
    public ResponseEntity<?> get(BusinessRequest businessRequests) {
        rateLimiterService.resolveBucket(businessRequests);
        return ResponseEntity.ok().build();
    }
}