package com.self.learn.caching.base;

import java.io.InputStream;

/**
 * 
 */
public interface Observer {

    /**
     * @param InputStream
     */
    public void updateContent(InputStream in);

    /**
     * @param Modification[*]
     */
    public void updateContent(Modification[] mods);

}