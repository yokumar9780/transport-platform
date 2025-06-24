package com.transport.platform.partnerservice.config.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;


@Configuration
@Slf4j
@ConditionalOnProperty(
        name = "spring.cache.type",
        havingValue = "redis",
        matchIfMissing = true
)
public class RedisCacheConfig {

    @Value("${spring.cache.redis.time-to-live}")
    private int ttl;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        log.info("Using Redis Cache Manager with TTL: {} seconds", ttl);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(ttl));
        //.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
        //.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // OBJECT MAPPER IS NOT WORKING
        //return RedisCacheManager.create(connectionFactory);
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();

    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setPassword(RedisPassword.of(redisPassword));
        return new JedisConnectionFactory(config);
    }


}
