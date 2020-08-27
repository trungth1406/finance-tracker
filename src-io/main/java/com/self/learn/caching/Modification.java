package com.self.learn.caching;

import java.util.*;

/**
 * 
 */
public abstract class Modification {

    /**
     * 
     */
    protected int lineNumer;

    /**
     * 
     */
    protected String content;

    /**
     * 
     */
    protected  abstract  void onContentModified();

    /**
     * @return
     */
    public abstract String getRange();

    /**
     * @return
     */
    public abstract String getContent();

}