package com.self.learn.caching.base;

import java.util.*;

/**
 * 
 */
public interface ContentWatcher {


    /**
     * 
     */
    public void notifyOserver();

    /**
     * 
     */
    public void addObserver();

    /**
     * 
     */
    public void removeObserver();

}