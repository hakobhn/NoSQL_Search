package com.epam.training.nosql.redis_cache.ratelimit;

import com.epam.training.nosql.redis_cache.ratelimit.resources.RateLimitController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RateLimiterTests {

    @Autowired
    private RateLimitController rateLimiter;

    @Test
    void testGetRateLimitedInfo() {

        // calls the method 10 times for user 1
        for (int i = 0; i < 10; i++) {
            rateLimiter.getLimitedInfo(1);
            rateLimiter.getLimitedInfo(5);
        }

        // verifies that the response is rate limited for user 1
        assertEquals("Rate limit exceeded", rateLimiter.getLimitedInfo(1));

        // verifies that the response is successful for user 2
        assertEquals("Hello 2", rateLimiter.getLimitedInfo(2));
    }

}
