package com.self.learn.caching.base;

import com.self.learn.caching.state.Modification;

import java.io.InputStream;

/**
 * 
 */
public interface ContentObserver {

    /**
     * @param InputStream
     */
    public void updateContent(InputStream in);

    /**
     * @param Modification[*]
     */
    public void updateContent(Modification[] mods);

}