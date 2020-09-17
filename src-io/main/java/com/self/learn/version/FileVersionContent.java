package com.self.learn.version;

import java.util.List;


public class FileVersionContent implements VersionContent {

    private List<LineVersion> lines;

    public FileVersionContent(List<LineVersion> lines) {
        this.lines = lines;
    }




}
