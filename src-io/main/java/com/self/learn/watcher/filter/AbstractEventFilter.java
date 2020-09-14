package com.self.learn.watcher.filter;

import com.self.learn.caching.base.CachingProxyImpl;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractEventFilter {

    protected CachingProxyImpl cachingProxy = new CachingProxyImpl();
    protected static final AtomicInteger integer = new AtomicInteger(1);

    protected static final String CACHE_NAME = "fileVersion";




    protected Integer nextVersion() {
        return integer.getAndIncrement();
    }

    protected Integer currentVersion() {
        return integer.get();
    }
}
