package com.self.learn.caching.base;

import com.self.learn.state.Modification;
import org.ehcache.Cache;

import java.util.Optional;

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