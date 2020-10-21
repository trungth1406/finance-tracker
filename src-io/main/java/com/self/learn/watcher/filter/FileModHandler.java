package com.self.learn.watcher.filter;

import com.self.learn.importer.ContentObserver;
import com.self.learn.state.Create;
import com.self.learn.state.Line;
import com.self.learn.state.ModificationState;
import com.self.learn.watcher.base.Watcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileModHandler extends AbstractEventHandler implements EventHandle, Watcher {

    private FileModHandler(List<ContentObserver> observers) {
        this.observers = observers;
    }

    public static FileModHandler with(ContentObserver... observers) {
        return new FileModHandler(Arrays.asList(observers));
    }


    // STOPSHIP: 10/20/20 Check for bug when changes were made in a file and line content is now incorrect
    @Override
    public void process(String fileName) {
        Queue<Line> cachedVersion = this.cachingProxy.getLastCachedContent(this.getCacheName(fileName));
        if (cachedVersion == null) return;
        File modified = new File(fileName);
        if (modified.isDirectory())
            return;
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            ArrayDeque<Line> newVersion = new ArrayDeque<>();
            for (int i = 0; i < lines.size(); i++) {
                Line line = new Line(i + 1, lines.get(i));
                newVersion.add(line);
            }

            List<Line> finalLines = new ArrayList<>();
            Line first, second;
            ModificationState mod;
            while (newVersion.size() > 0 && cachedVersion.size() > 0) {
                first = newVersion.remove();
                second = cachedVersion.remove();
                mod = first.getModificationState().diff(second.getModificationState());
                first.changeState(mod);
                finalLines.add(first);
            }

            newVersion.forEach(remainLine -> { remainLine.
                    changeState(new Create(remainLine.getLineNumber(), remainLine.getContent()));
                    finalLines.add(remainLine);
                    }
            );

            observers.forEach(contentObserver -> contentObserver.updateContent(finalLines, fileName));
            this.cachingProxy.updateCacheContent(this.getCacheName(fileName), newVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // STOPSHIP: 10/5/20 Test update service
    @Override
    public void notifyObserver() {

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