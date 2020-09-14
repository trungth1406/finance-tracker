package com.self.learn.caching.base;

import com.google.common.collect.Queues;
import com.self.learn.version.Line;
import com.self.learn.version.VersionContent;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import javax.swing.text.html.Option;
import java.util.*;

public class CachingProxyImpl implements CachingProxy<Queue<Line>> {


    private CacheManager cacheManager;
    private Cache<String, List> fileVersionCache;

    public CachingProxyImpl() {
        this.cacheManager = initCacheManager().build();
        this.cacheManager.init();

        this.fileVersionCache = this.cacheManager.getCache("fileVersion",
                String.class, List.class);
    }

    private static CacheManagerBuilder<CacheManager> initCacheManager() {
        return CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("fileVersion",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class, List.class,
                                ResourcePoolsBuilder.heap(10).build()));
    }


    public Cache<String, List> getFileVersionCache() {
        return fileVersionCache;
    }


    @Override
    public void updateCacheContent(String cacheName, Queue<Line> lines) {
        List<Queue<Line>> cachedQueue = this.fileVersionCache.get(cacheName);
        if (cachedQueue == null) cachedQueue = new ArrayList<>();
        cachedQueue.add(lines);
        this.fileVersionCache.put(cacheName, cachedQueue);
    }

    @Override
    public void createNewCache(String cacheName, Queue<Line> lines) {
        List<Queue<Line>> cachedQueue = new ArrayList<>();
        cachedQueue.add(lines);
        this.fileVersionCache.put(cacheName, cachedQueue);
    }

    @Override
    public Queue<Line> getLastCachedContent(String cacheName) {
        List<Queue<Line>> cachedQueue = this.fileVersionCache.get(cacheName);
        return Optional.of(cachedQueue.get(cachedQueue.size() - 1)).orElse(Queues.newArrayDeque());
    }
}
