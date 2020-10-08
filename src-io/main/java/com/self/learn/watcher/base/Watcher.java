package com.self.learn.watcher.base;

import com.self.learn.importer.ContentObserver;

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
    public void addObserver(ContentObserver observer);

    /**
     * 
     */
    public void removeObserver(ContentObserver observer);

}