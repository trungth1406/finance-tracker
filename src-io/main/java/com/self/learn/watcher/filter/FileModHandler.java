package com.self.learn.watcher.filter;

import com.self.learn.state.Create;
import com.self.learn.state.Modification;
import com.self.learn.version.LineVersion;

import java.io.*;
import java.util.*;

public class FileModHandler extends AbstractEventHandler implements EventFilter {


    public FileModHandler() {
    }

    @Override
    public void process(String fileName) {
        ArrayDeque<LineVersion> newVersion = new ArrayDeque<>();
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            LineVersion lineObject;
            String line;
            while ((line = reader.readLine()) != null) {
                lineObject = new LineVersion(line.trim(), reader.getLineNumber());
                newVersion.add(lineObject);
            }

            ArrayDeque<LineVersion> cachedVersion = (ArrayDeque<LineVersion>)
                    this.cachingProxy.getLastCachedContent(generateCacheName());
            while (newVersion.size() > 0 && cachedVersion.size() > 0) {
                LineVersion first = newVersion.remove();
                LineVersion second = cachedVersion.remove();
                Modification mod = first.diff(second);
                System.out.println(mod);
                System.out.println(mod.getContent());
            }

            if (newVersion.size() > 0) {
                for (LineVersion remain : newVersion) {
                    Modification mod = new Create(remain.getLineNumer(), remain.getContent());
                    System.out.println(Arrays.deepToString(mod.getContent()));
                }
            }
            this.cachingProxy.updateCacheContent(generateCacheName(), newVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
