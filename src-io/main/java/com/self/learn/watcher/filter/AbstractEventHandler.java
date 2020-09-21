package com.self.learn.watcher.filter;

import com.self.learn.caching.base.CachingProxyImpl;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEventHandler {

    protected CachingProxyImpl cachingProxy = CachingProxyImpl.getInstance();
    protected static final AtomicInteger integer = new AtomicInteger(0);
    protected static final String CACHE_NAME = "fileVersion";

    protected Integer nextVersion() {
        return integer.incrementAndGet();
    }

    protected Integer currentVersion() {
        return integer.get();
    }

    protected String generateCacheName() {
        return String.format("%s_v_%d", CACHE_NAME, this.currentVersion());
    }
}
