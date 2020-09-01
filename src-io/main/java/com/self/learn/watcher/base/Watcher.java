package com.self.learn.watcher.base;

import java.util.*;

/**
 * 
 */
public interface Watcher {


    /**
     * 
     */
    public void notifyObserver();

    /**
     * 
     */
    public void addObserver();

    /**
     * 
     */
    public void removeObserver();

}