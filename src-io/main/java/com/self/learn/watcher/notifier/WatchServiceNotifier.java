package com.self.learn.watcher.notifier;

import com.self.learn.watcher.base.EventNotifier;
import com.self.learn.watcher.base.EventObserver;
import com.self.learn.watcher.base.Watcher;

import java.io.IOException;
import java.nio.file.*;

public final class WatchServiceNotifier implements Runnable, EventNotifier {

    private static Path path;
    private static WatchService watchService;

    private WatchServiceNotifier(Path path, Watcher notifying) {
        this.path = path;
        initWatchService();
    }

    public static WatchServiceNotifier from(Path path, Watcher notifying) {
        return new WatchServiceNotifier(path, notifying);
    }

    private static WatchService initWatchService() {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return watchService;
    }

    public void run() {
        WatchKey key;
        while (true) {
            try {
                if (!((key = watchService.take()) != null)) break;
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
                    this.notifyRequest(event);
                }
                key.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyRequest(WatchEvent watchEvent) {
        this.eventObserver.stream().forEach(observer -> observer.processWith(watchEvent));
    }

    @Override
    public void addEventObserver(EventObserver eventObserver) {
        this.eventObserver.add(eventObserver);
    }
}
