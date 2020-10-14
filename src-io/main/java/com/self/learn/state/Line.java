package com.self.learn.state;

import org.apache.tomcat.util.buf.StringUtils;

public class Line {

    private final int lineNumber;
    private String content;
    private ModificationState modificationState;

    public Line(int lineNumber, String content) {
        this.lineNumber = lineNumber;
        this.content = content;
        this.modificationState = new Unchanged(lineNumber, content);
    }


    public int getLineNumber() {
        return lineNumber;
    }

    public String getContent() {
        String[] content = this.modificationState.getContent();
        this.content = StringUtils.join(content);
        return this.content;
    }

    public ModificationState getModificationState() {
        return modificationState;
    }

    public void changeState(ModificationState modificationState) {
        this.modificationState = modificationState;
    }
}
