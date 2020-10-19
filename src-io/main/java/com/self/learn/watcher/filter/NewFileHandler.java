package com.self.learn.watcher.filter;

import com.self.learn.google.api.service.BaseSheetService;
import com.self.learn.importer.GoogleSheetObserver;
import com.self.learn.state.Line;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class NewFileHandler extends AbstractEventHandler implements EventHandle {


    public NewFileHandler() {
        this.observers.add(new GoogleSheetObserver(new BaseSheetService()));
    }

    @Override
    public void process(String fileName) {
        File modified = new File(fileName);
        if (modified.isDirectory()) return;
        List<Line> newVersion = new ArrayList<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(modified)))) {
            Line line;
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                line = new Line(reader.getLineNumber(), newLine.trim());
                newVersion.add(line);
            }
            this.observers.stream().forEach(observer -> observer.create(newVersion,fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.cachingProxy.createNewCache(this.getCacheName(fileName), new ArrayDeque<>(newVersion));
        }
    }
}
