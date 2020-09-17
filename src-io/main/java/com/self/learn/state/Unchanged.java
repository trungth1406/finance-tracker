package com.self.learn.state;

public class Unchanged extends Modification {
    public Unchanged(int lineNumber, String content) {
        super(lineNumber, content);
    }

    @Override
    protected Modification processContent() {
        return this;
    }


}
