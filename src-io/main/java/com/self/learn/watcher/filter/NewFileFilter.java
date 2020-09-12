package com.self.learn.watcher.filter;

public class NewFileFilter implements EventFilter {

    @Override
    public void process(String fileName) {
        System.out.println("New file: " + fileName);
    }
}
