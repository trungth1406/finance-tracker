package com.self.learn.caching.base;

import com.google.common.collect.Queues;
import com.self.learn.version.LineVersion;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.*;

public class CachingProxyImpl implements CachingProxy<Queue<LineVersion>> {


    private static CachingProxyImpl INSTANCE = null;
    private static final CacheManager cacheManager = initCacheManager().build();
    private static Cache<String, LinkedList> fileVersionCache;

    public static CachingProxyImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CachingProxyImpl();
            cacheManager.init();
            fileVersionCache = cacheManager.getCache("fileVersion",
                    String.class, LinkedList.class);
        }
        return INSTANCE;
    }

    private static CacheManagerBuilder<CacheManager> initCacheManager() {
       //TODO: Add recovery configuration
        return CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("fileVersion",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, LinkedList.class,
                                ResourcePoolsBuilder.heap(10).build()));
    }

    @Override
    public void updateCacheContent(String cacheName, Queue<LineVersion> lines) {
        LinkedList<Queue<LineVersion>> cachedQueue = this.fileVersionCache.get(cacheName);
        if(cachedQueue == null) throw new IllegalArgumentException("No such cache");
        cachedQueue.add(lines);
        this.fileVersionCache.put(cacheName, cachedQueue);
    }

    @Override
    public void createNewCache(String cacheName, Queue<LineVersion> lines) {
        LinkedList<Queue<LineVersion>> cachedQueue = new LinkedList<>();
        cachedQueue.add(lines);
        this.fileVersionCache.put(cacheName, cachedQueue);
    }

    @Override
    public Queue<LineVersion> getLastCachedContent(String cacheName) {
        List<Queue<LineVersion>> cachedQueue = this.fileVersionCache.get(cacheName);
        if (Optional.ofNullable(cachedQueue).isPresent()) {
            return cachedQueue.get(0);
        }
        return Queues.newArrayDeque();
    }
}
