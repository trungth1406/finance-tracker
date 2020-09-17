package com.self.learn.watcher.filter;

import com.self.learn.state.Create;
import com.self.learn.state.Modification;
import com.self.learn.version.LineVersion;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class NewFileHandler extends AbstractEventHandler implements EventFilter {

    @Override
    public void process(String fileName) {
        if (!fileName.endsWith(".txt")) {
            throw new IllegalArgumentException("Accept text file only at this point");
        }

        Queue<LineVersion> newVersion = new ArrayDeque<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            LineVersion line;
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                line = new LineVersion(newLine.trim(), reader.getLineNumber());
                newVersion.add(line);
            }

            List<Modification> modificationList = new ArrayList<>();
            for (LineVersion each : newVersion) {
                modificationList.add(new Create(each.getLineNumer(), each.getContent()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.cachingProxy.createNewCache(this.generateCacheName(), newVersion);
        }
    }
}
