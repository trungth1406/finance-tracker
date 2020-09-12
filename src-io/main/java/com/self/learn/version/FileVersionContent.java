package com.self.learn.version;

import java.util.List;


public class FileVersionContent implements VersionContent {

    private List<Line> lines;

    public FileVersionContent(List<Line> lines) {
        this.lines = lines;
    }




}
