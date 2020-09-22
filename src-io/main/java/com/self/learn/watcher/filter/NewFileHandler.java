package com.self.learn.watcher.filter;

import com.self.learn.state.Modification;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class NewFileHandler extends AbstractEventHandler implements EventHandle {



    @Override
    public void process(String fileName) {
        if (!fileName.endsWith(".txt")) {
            throw new IllegalArgumentException("Accept text file only at this point");
        }
        File modified = new File(fileName);
        if(modified.isDirectory()) return;
        Queue<Modification> newVersion = new ArrayDeque<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(modified)))) {
            Modification line;
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                if (newLine.isEmpty()) continue;
                line = new Modification(reader.getLineNumber(), newLine.trim()).processContent();
                newVersion.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.cachingProxy.createNewCache(this.getCacheName(fileName), newVersion);
        }
    }
}
