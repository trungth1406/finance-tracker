package com.self.learn.watcher.filter;

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
        Queue<Line> newVersion = new ArrayDeque<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            Line line;
            while (reader.read() != -1) {
                line = new Line(reader.readLine(), reader.getLineNumber());
                newVersion.add(line);
            }
            this.cachingProxy.updateCacheContent(CACHE_NAME,newVersion);

            Queue<Line> cachedVersion = this.cachingProxy.getLastCachedContent(CACHE_NAME);
            while (newVersion.size() > 0 && cachedVersion.size() > 0) {
                Modification mod = newVersion.remove().diff(cachedVersion.remove());
                System.out.println(mod);
            }

            if(newVersion.size() > 0){
                for(Line remain : newVersion){
                    Modification mod = new Create(remain.getLineNumer(),remain.getContent());
                    System.out.println(Arrays.deepToString(mod.getContent()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
