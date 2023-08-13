package com.epam.training.nosql.redis_cache.ratelimit.model;

import lombok.Data;

@Data
public class BusinessRequest {
    private String accountId;
    private String clientIp;
    private String requestType;
}
