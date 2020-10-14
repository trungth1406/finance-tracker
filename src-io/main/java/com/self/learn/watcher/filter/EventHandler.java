package com.self.learn.watcher.filter;

import com.self.learn.google.api.service.BaseSheetService;
import com.self.learn.importer.GoogleSheetObserver;
import com.self.learn.watcher.base.EventObserver;

import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.Collections;

public class EventHandler implements EventObserver {

    private EventHandle filter;

    public EventHandler() {
    }

    private static boolean isOfType(WatchEvent.Kind<Path> entry, WatchEvent watchEvent) {
        return entry.name().equals(watchEvent.kind().name());
    }

    // STOPSHIP: 10/8/20 Handle modification with its corresponding service 
    @Override
    public synchronized void processWith(WatchEvent watchEvent) {
        if (isOfType(StandardWatchEventKinds.ENTRY_CREATE, watchEvent)) {
            this.filter = new NewFileHandler();
        } else if (isOfType(StandardWatchEventKinds.ENTRY_MODIFY, watchEvent)) {
            this.filter = FileModHandler.with((new GoogleSheetObserver(new BaseSheetService())));
        } else {
            return;
        }
        this.filter.process(watchEvent.context().toString());
    }
}
