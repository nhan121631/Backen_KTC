package com.example.demo.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
public class CacheConfig {
        @Bean
        public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
                GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

                RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(60))
                                .disableCachingNullValues()
                                .serializeKeysWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(
                                                RedisSerializationContext.SerializationPair.fromSerializer(serializer));

                return RedisCacheManager.builder(redisConnectionFactory)
                                .cacheDefaults(config)
                                .build();
        }
}