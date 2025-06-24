package com.transport.platform.partnerservice.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
@Slf4j
@ConditionalOnProperty(
        name = "spring.cache.type",
        havingValue = "simple",
        matchIfMissing = true
)
public class SimpleCacheConfig {
    @Value("${spring.cache.redis.time-to-live}")
    private int ttl;

    @Bean
    public CacheManager cacheManager() {
        log.info("Using Simple Cache Manager with TTL: {} seconds", ttl);
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .expireAfterWrite(ttl, TimeUnit.SECONDS)
                        .maximumSize(1000)
        );
        return cacheManager;
    }
}
