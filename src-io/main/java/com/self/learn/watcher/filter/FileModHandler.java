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

        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            ArrayDeque<Modification> newVersion = new ArrayDeque<>();
            Modification line;
            String newLine;
            while ((newLine = reader.readLine()) != null) {
                if (newLine.isEmpty()) continue;
                line = new Create(reader.getLineNumber(), newLine.trim()).processContent();
                newVersion.add(line);
            }

            Queue<Modification> cachedVersion = this.cachingProxy.getLastCachedContent(generateCacheName());
            Modification first, second, mod;
            while (newVersion.size() > 0 && cachedVersion.size() > 0) {
                first = newVersion.remove();
                second = cachedVersion.remove();
                mod = first.diff(second);
                System.out.println(mod);
                System.out.println(mod.getRange('A'));
                System.out.println(Arrays.deepToString(mod.getContent()));
            }

//            if (newVersion.size() > 0) {
//                for (Modification remain : newVersion) {
//                   Modification mod = new Create(, remain.getContent());
//                    System.out.println(Arrays.deepToString(mod.getContent()));
//                }
//            }
            this.cachingProxy.updateCacheContent(String.format("%s_%s_v_%d", CACHE_NAME, fileName, this.nextVersion()), newVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}