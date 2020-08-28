package com.self.learn.caching.state;

import com.self.learn.caching.base.Modification;

/**
 *
 */
public class Update extends Modification {

    public Update(int lineNumer, String content) {
        super(lineNumer, content);
    }

    /**
     *
     */
    protected Modification processContent() {
        //TODO: Process String content. append;
        return null;
    }

    /**
     * @return
     * @param fromCol
     */
    public String getRange(char fromCol) {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public String[] getContent() {
        // TODO implement here
        return null;
    }

}