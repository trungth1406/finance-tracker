package com.self.learn.watcher.filter;

import com.self.learn.state.Modification;
import com.self.learn.version.Line;

import java.io.*;
import java.util.*;

public class FileModificationHandler implements EventFilter {


    public FileModificationHandler() {
    }

    @Override
    public void process(String fileName) {
        try (LineNumberReader reader =
                     new LineNumberReader(
                             new InputStreamReader(new FileInputStream(fileName)))) {
            Queue<Line> lines = new ArrayDeque<>();
            Queue<Line> lines2 = new ArrayDeque<>();
            lines2.add(new Line("0",1));
            lines2.add(new Line("1",2));
            Line line;
            while (reader.read() != -1) {
                line = new Line(reader.readLine(), reader.getLineNumber());
                lines.add(line);
            }
            while (lines.size() > 0 && lines2.size() > 0) {
                Modification mod = lines.remove().diff(lines2.remove());
                System.out.println(mod.getRange('A'));
                System.out.println(Arrays.deepToString(mod.getContent()));
            }

            if(lines2.size() > 0 ){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File changes: " + fileName);
    }

}
