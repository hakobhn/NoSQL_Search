package com.epam.training.nosql.redis_cache.ratelimit.resources;

import com.epam.training.nosql.redis_cache.ratelimit.model.BusinessRequest;
import com.epam.training.nosql.redis_cache.ratelimit.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RateLimitController {

    private final RateLimiterService rateLimiterService;

    @PostMapping("/")
    public ResponseEntity<?> get(List<BusinessRequest> businessRequests) {
        rateLimiterService.resolveBucket(businessRequests);
        return ResponseEntity.ok().build();
    }
}