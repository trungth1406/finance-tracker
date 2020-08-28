package com.self.learn.caching.state;

import com.self.learn.caching.base.Modification;

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