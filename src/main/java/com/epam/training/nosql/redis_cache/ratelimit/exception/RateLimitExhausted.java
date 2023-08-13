package com.epam.training.nosql.redis_cache.ratelimit.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS, reason = "Too many requests")
public class RateLimitExhausted extends RuntimeException {
}
