package com.self.learn.caching.base;

/**
 * 
 */
public interface CachingProxy<CacheContent> {


    /**
     * @return
     */
    void updateCacheContent(String cacheName,CacheContent content);

    void createNewCache(String cacheName,CacheContent content);

    CacheContent getLastCachedContent(String cacheName);

}