package com.self.learn.watcher.base;

import java.nio.file.WatchEvent;

public interface EventObserver {

    void processWith(WatchEvent watchEvent);

}
