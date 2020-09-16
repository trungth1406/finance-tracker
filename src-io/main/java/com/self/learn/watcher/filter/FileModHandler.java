package com.self.learn.watcher.filter;

import com.google.common.collect.Queues;
import com.self.learn.caching.base.CachingProxy;
import com.self.learn.caching.base.CachingProxyImpl;
import com.self.learn.state.Create;
import com.self.learn.state.Modification;
import com.self.learn.version.Line;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.*;

public class FileModHandler extends AbstractEventFilter implements EventFilter {


    public FileModHandler() {
    }

    @Override
    public void process(String fileName) {
        ArrayDeque<Line> newVersion = new ArrayDeque<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            Line lineObject;
            String line;
            while ((line = reader.readLine()) != null) {
                lineObject = new Line(line, reader.getLineNumber());
                newVersion.add(lineObject);
            }

            ArrayDeque<Line> cachedVersion = (ArrayDeque<Line>) this.cachingProxy.getLastCachedContent(CACHE_NAME);
            while (newVersion.size() > 0 && cachedVersion.size() > 0) {
                Line first = newVersion.remove();
                Line second = cachedVersion.remove();
                Modification mod = first.diff(second);
                System.out.println(mod);
            }

            if (newVersion.size() > 0) {
                for (Line remain : newVersion) {
                    Modification mod = new Create(remain.getLineNumer(), remain.getContent());
                    System.out.println(Arrays.deepToString(mod.getContent()));
                }
            }
            this.cachingProxy.updateCacheContent(CACHE_NAME, newVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
