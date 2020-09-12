package com.self.learn.caching.base;

import com.self.learn.state.Modification;

import java.io.InputStream;

/**
 * 
 */
public interface ContentObserver {

    /**
     * @param in
     */
    public void updateContent(InputStream in);

    /**
     * @param mods
     */
    public void updateContent(Modification[] mods);

}