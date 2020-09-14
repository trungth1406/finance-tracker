package com.self.learn.watcher.impl;

import com.self.learn.state.Modification;
import com.self.learn.importer.ContentObserver;
import com.self.learn.watcher.base.Watcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * TODO: TRY MEDIATOR PATTERN
 */
public class FileWatcher implements Watcher {

    public FileWatcher() {
    }

    private static PriorityQueue<Modification> priorityQueue;
    private List<ContentObserver> contentObservers;


    /**
     *
     */
    public void enqueue(Modification modification) {
        ContentObserver[] localArr = (ContentObserver[]) contentObservers.toArray();
        for (int i = 0; i < contentObservers.size(); i++) {
            try {
                localArr[i].updateContent(new FileInputStream(""));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    public void notifyObserver() {

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