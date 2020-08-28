package com.self.learn.caching;

import com.self.learn.caching.base.ContentWatcher;
import com.self.learn.caching.base.Modification;
import com.self.learn.caching.base.Observer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 */
public class FileWatcher implements ContentWatcher{
    /**
     * Default constructor
     */
    public FileWatcher() {
    }

    /**
     * 
     */
    private static PriorityQueue<Modification> priorityQueue;
    private List<Observer> observers;


    /**
     * 
     */
    protected void enqueue() {
        Observer[] localArr = (Observer[]) observers.toArray();
        for(int i = 0 ; i < observers.size();i++){
            try {
                localArr[i].updateContent(new FileInputStream(""));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // TODO implement here
    }

    /**
     * 
     */
    public void notifyOserver() {

    }

    /**
     * 
     */
    public void addObserver() {
        // TODO implement here
    }

    /**
     * 
     */
    public void removeObserver() {
        // TODO implement here
    }


}