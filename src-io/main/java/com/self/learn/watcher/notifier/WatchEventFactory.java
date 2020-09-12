package com.self.learn.watcher.notifier;

import com.self.learn.state.Modification;
import com.self.learn.state.Create;

import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;

public class WatchEventFactory {

    private WatchEvent watchEvent;

    private WatchEventFactory(WatchEvent watchEvent) {
        this.watchEvent = watchEvent;
    }

    public static WatchEventFactory from(WatchEvent event) {
        return new WatchEventFactory(event);
    }


    public Modification getType(int lineNumber, String content) {
        return getEventType(lineNumber, content, null);
    }

    private Modification getEventType(int lineNumber, String content, StandardWatchEventKinds kind) {
        return null;
    }
}
