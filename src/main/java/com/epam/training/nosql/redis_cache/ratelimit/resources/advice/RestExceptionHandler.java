package com.epam.training.nosql.redis_cache.ratelimit.resources.advice;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    @Value("${bucket.refresh.duration}")
    private Duration refreshDuration;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException ex) {
        log.warn("handling filed validation exception: {}", ex.getMessage());
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors
                        .toMap(FieldError::getField,
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                (value1, value2) -> value1)
                );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(fieldErrors);
    }

//    @ExceptionHandler({RateLimitExhausted.class})
//    public ResponseEntity<?> handleNotFound(Exception ex, WebRequest request) {
//        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
//                .header("X-Rate-Limit-Retry-After-Seconds", String.format(waitForRefill))
//                .build();
//    }
}
