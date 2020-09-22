package com.self.learn.watcher.filter;

import com.self.learn.caching.base.CachingProxyImpl;

import java.util.concurrent.atomic.AtomicInteger;


//TODO: Refactor this into a better encapsulation
public abstract class AbstractEventHandler {

    protected CachingProxyImpl cachingProxy = CachingProxyImpl.getInstance();
    protected static final AtomicInteger integer = new AtomicInteger(1);
    protected static final String CACHE_NAME = "fileVersion";

    protected Integer nextVersion() {
        return integer.incrementAndGet();
    }

    protected Integer currentVersion() {
        return integer.get();
    }

    protected String getCacheName(String fileName) {
        return String.format("%s_%s_v_%d", CACHE_NAME, fileName, 1);
    }


}