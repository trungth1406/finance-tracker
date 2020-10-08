package com.self.learn.watcher.filter;

import com.self.learn.importer.ContentObserver;
import com.self.learn.state.Create;
import com.self.learn.state.Modification;
import com.self.learn.watcher.base.Watcher;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FileModHandler extends AbstractEventHandler implements EventHandle, Watcher {


    private final List<Modification> modifications = new ArrayList<>();

    private FileModHandler(List<ContentObserver> observers) {
        this.observers = observers;
    }

    public static FileModHandler with(List<ContentObserver> observers) {
        return new FileModHandler(observers);
    }

    @Override
    public void process(String fileName) {
        Queue<Modification> cachedVersion =
                this.cachingProxy.getLastCachedContent(getCacheName(fileName));
        File modified = new File(fileName);
        if (modified.isDirectory()) return;
        try (LineNumberReader reader = new LineNumberReader(
                new InputStreamReader(new FileInputStream(modified)))) {
            ArrayDeque<Modification> newVersion = new ArrayDeque<>();
            Modification line;
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                if (newLine.isEmpty()) continue;
                line = new Create(reader.getLineNumber(), newLine.trim()).processContent();
                newVersion.add(line);
            }

            this.cachingProxy.updateCacheContent(this.getCacheName(fileName), newVersion);

            Modification first, second, mod;
            //TODO: Add logic for getting changes from new version and cachedVersion
            while (newVersion.size() > 0 && cachedVersion.size() > 0) {
                first = newVersion.remove();
                second = cachedVersion.remove();
                mod = first.diff(second);
                this.modifications.add(mod);
            }

            if (newVersion.size() > 0) {
                for (Modification remain : newVersion) {
                    this.modifications.add(remain);
                }
            }
            this.notifyObserver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // STOPSHIP: 10/5/20 Test update service
    @Override
    public void notifyObserver() {
        observers.stream().forEach(contentObserver -> contentObserver.updateContent(this.modifications));
    }

    @Override
    public void addObserver(ContentObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(ContentObserver observer) {
        this.observers.remove(observer);
    }
}