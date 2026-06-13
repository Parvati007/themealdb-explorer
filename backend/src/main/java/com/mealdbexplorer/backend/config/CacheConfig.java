package com.mealdbexplorer.backend.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
// Define cache regions used in the application
        CaffeineCacheManager manager =
                new CaffeineCacheManager(
                        "mealSearch",
                        "mealDetails",
                        "categories",
                        "mealsByCategory");

        manager.setCaffeine(
                Caffeine.newBuilder()
                        .maximumSize(100)
                        .expireAfterWrite(10, TimeUnit.MINUTES)
        );
// Prevents excessive memory usage by limiting cache size        
// Cached data expires 10 minutes after being written
// Ensures users get reasonably fresh API data
        return manager;
    }
}