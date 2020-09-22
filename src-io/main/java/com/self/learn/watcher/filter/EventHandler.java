package com.self.learn.watcher.filter;

import com.self.learn.watcher.base.EventObserver;

import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;

public class EventHandler implements EventObserver {

    private EventHandle filter;

    public EventHandler() {
    }

    @Override
    public synchronized void processWith(WatchEvent watchEvent) {
        if (isOfType(StandardWatchEventKinds.ENTRY_CREATE, watchEvent)) {
            this.filter = new NewFileHandler();
        } else if (isOfType(StandardWatchEventKinds.ENTRY_MODIFY, watchEvent)) {
            this.filter = new FileModHandler(watcher);
        }else{
            return;
        }
        this.filter.process(watchEvent.context().toString());
    }

    private static boolean isOfType(WatchEvent.Kind<Path> entry, WatchEvent watchEvent) {
        return entry.name().equals(watchEvent.kind().name());
    }
}
