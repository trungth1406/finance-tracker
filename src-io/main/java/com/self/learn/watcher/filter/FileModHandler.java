package com.self.learn.watcher.filter;

import com.self.learn.importer.ContentObserver;
import com.self.learn.state.Create;
import com.self.learn.state.Line;
import com.self.learn.state.ModificationState;
import com.self.learn.watcher.base.Watcher;

import java.io.*;
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
        try (LineNumberReader reader = new LineNumberReader(
                new InputStreamReader(new FileInputStream(modified)))) {
            ArrayDeque<Line> newVersion = new ArrayDeque<>();
            Line line;
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                line = new Line(reader.getLineNumber(), newLine.trim());
                newVersion.add(line);
            }

            List<Line> lines = new ArrayList<>();
            Line first, second;
            ModificationState mod;
            while (newVersion.size() > 0 && cachedVersion.size() > 0) {
                first = newVersion.remove();
                second = cachedVersion.remove();
                mod = first.getModificationState().diff(second.getModificationState());
                first.changeState(mod);
                lines.add(first);
            }

            newVersion.forEach(remainLine -> {
                        remainLine.changeState(new Create(remainLine.getLineNumber(), remainLine.getContent()));
                        lines.add(remainLine);
                    }
            );

            observers.forEach(contentObserver -> contentObserver.updateContent(lines, fileName));
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