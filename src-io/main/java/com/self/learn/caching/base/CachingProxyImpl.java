package com.self.learn.caching.base;

import com.self.learn.state.Line;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class CachingProxyImpl implements CachingProxy<Queue<Line>> {


    private static final CacheManager cacheManager = initCacheManager().build();
    private static CachingProxyImpl INSTANCE = null;
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


    // STOPSHIP: 10/15/20 Change Caching logics 
    @Override
    public void updateCacheContent(String cacheName, Queue<Line> lines) {
        //TODO: Change rule when get and replace versions of Lines
        LinkedList<Queue<Line>> cachedQueue = fileVersionCache.get(cacheName);
        if (cachedQueue == null) throw new IllegalArgumentException("No such cache");
        cachedQueue.add(lines);
        fileVersionCache.put(cacheName, cachedQueue);
    }

    @Override
    public void createNewCache(String cacheName, Queue<Line> lines) {
        LinkedList<Queue<Line>> cachedQueue = new LinkedList<>();
        cachedQueue.add(lines);
        fileVersionCache.put(cacheName, cachedQueue);
    }

    @Override
    public Queue<Line> getLastCachedContent(String cacheName) {
        List<Queue<Line>> cachedQueue = fileVersionCache.get(cacheName);
        if (Optional.ofNullable(cachedQueue).isPresent()) {
            return cachedQueue.get(cachedQueue.size() - 1);
        } else {
            return null;
        }
    }
}
