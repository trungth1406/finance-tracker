package com.self.learn.watcher.filter;

import com.self.learn.state.Create;
import com.self.learn.state.Modification;
import com.self.learn.version.Line;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class NewFileHandler extends AbstractEventFilter implements EventFilter {

    @Override
    public void process(String fileName) {
        if (!fileName.endsWith(".txt")) {
            throw new IllegalArgumentException("Accept text file only at this point");
        }

        Queue<Line> newVersion = new ArrayDeque<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            Line line;
            while (reader.read() != -1) {
                line = new Line(reader.readLine(), reader.getLineNumber());
                newVersion.add(line);
            }

            List<Modification> modificationList = new ArrayList<>();
            for (Line each : newVersion) {
                modificationList.add(new Create(each.getLineNumer(), each.getContent()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.cachingProxy.createNewCache(CACHE_NAME, newVersion);
        }
    }
}
