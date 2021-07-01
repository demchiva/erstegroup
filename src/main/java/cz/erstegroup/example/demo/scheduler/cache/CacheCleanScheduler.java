package cz.erstegroup.example.demo.scheduler.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static cz.erstegroup.example.demo.config.cache.CacheConfig.PRODUCTS_CACHE_NAME;
import static cz.erstegroup.example.demo.config.cache.CacheConfig.TRANSPARENT_ACCOUNTS_CACHE_NAME;

/**
 * Scheduler clean the cache for transparent accounts and products.
 */
@Component
public class CacheCleanScheduler {

    private final CacheManager cacheManager;

    public CacheCleanScheduler(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * Clears cache for transparent accounts at the beginning of every hour.
     */
    @Scheduled(cron = "0 0 * * * *")
    private void clearTransparentAccountsCache() {
        final Cache cache = cacheManager.getCache(TRANSPARENT_ACCOUNTS_CACHE_NAME);
        if (cache != null) {
            cache.clear();
        }
    }
    /**
     * Clears cache for products every month on the 1st, at noon.
     */
    @Scheduled(cron = "0 0 12 1 * ?")
    private void clearProductsCache() {
        final Cache cache = cacheManager.getCache(PRODUCTS_CACHE_NAME);
        if (cache != null) {
            cache.clear();
        }
    }
}
