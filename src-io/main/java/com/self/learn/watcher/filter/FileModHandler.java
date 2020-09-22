package com.self.learn.watcher.filter;

import com.self.learn.importer.ContentObserver;
import com.self.learn.state.Create;
import com.self.learn.state.Modification;
import com.self.learn.watcher.base.Watcher;

import java.io.*;
import java.util.*;

public class FileModHandler extends AbstractEventHandler implements EventHandle, Watcher {


    private Watcher watcher;
    private List<ContentObserver> observers;
    private List<Modification> modifications = new ArrayList<>();

    public FileModHandler(Watcher watcher) {
        this.watcher = watcher;
    }

    @Override
    public void process(String fileName) {
        Queue<Modification> cachedVersion =
                this.cachingProxy.getLastCachedContent(getCacheName(fileName));
        File modified = new File(fileName);
        if (modified.isDirectory()) return;
        try (LineNumberReader reader =
                     new LineNumberReader(
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
            //TODO: Add logic for getting changes from newversion and cachedVersion
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


    @Override
    public void notifyObserver() {
        observers.stream().forEach(contentObserver -> contentObserver.updateContent(this.modifications));
    }

    @Override
    public void addObserver() {

    }

    @Override
    public void removeObserver() {

    }
}