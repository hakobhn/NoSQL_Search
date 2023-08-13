package com.epam.training.nosql.redis_cache.ratelimit.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.concurrent.TimeUnit;


@Data
public class RateLimitRule {
    // Account identifier integer, for example very_important_customer
    private String accountId;
    // Client ip address, for example 127.0.0.1
    private String clientIp;
    // String, for example login
    private String requestType;
    // Integer, indicating max allowed request per given interval, for example 10
    @NotNull(message = "Allowed number of requests is mandatory")
    private Integer allowedNumberOfRequests;
    // Time unit enum value, for example SECONDS, MINUTES, DAYS, etc
    @NotNull(message = "Time interval is mandatory")
    private TimeUnit timeInterval;
}
