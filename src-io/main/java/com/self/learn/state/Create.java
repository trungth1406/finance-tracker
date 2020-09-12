package com.self.learn.state;

/**
 *
 */
public class Create extends Modification {

    public Create(int lineNumer, String content) {
        super(lineNumer, content);
    }

    /**
     *
     */
    protected Modification processContent() {
        return this;
    }


}