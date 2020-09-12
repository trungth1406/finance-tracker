package com.self.learn.state;

/**
 *
 */
public class Delete extends Modification {

    public Delete(int lineNumer, String content) {
        super(lineNumer, content);
    }

    /**
     *
     */
    protected Modification processContent() {
        return this;
    }


    /**
     * @return
     */
    public String[] getContent() {
        return new String[]{"","","",""};
    }

}