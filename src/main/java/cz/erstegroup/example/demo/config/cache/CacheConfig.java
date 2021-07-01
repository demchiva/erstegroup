package cz.erstegroup.example.demo.config.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for cache management.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    public static final String TRANSPARENT_ACCOUNTS_CACHE_NAME = "transparentAccounts";
    public static final String PRODUCTS_CACHE_NAME = "products";

    /**
     * Creates 2 caches for storing transparent accounts and czech statistical center products.
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(TRANSPARENT_ACCOUNTS_CACHE_NAME, PRODUCTS_CACHE_NAME);
    }
}
