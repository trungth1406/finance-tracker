package com.self.learn.watcher.filter;

import com.self.learn.importer.impl.FileInputImporter;
import com.self.learn.state.Create;
import com.self.learn.state.Modification;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

public class NewFileHandler extends AbstractEventHandler implements EventFilter {

    @Override
    public void process(String fileName) {
        if (!fileName.endsWith(".txt")) {
            throw new IllegalArgumentException("Accept text file only at this point");
        }

        Queue<Modification> newVersion = new ArrayDeque<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            Modification line;
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                if (newLine.isEmpty()) continue;
                line = new Create(reader.getLineNumber(), newLine.trim()).processContent();
                newVersion.add(line);
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
