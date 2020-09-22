package com.self.learn.importer;

import com.self.learn.state.Modification;

import java.io.InputStream;
import java.util.List;

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
    public void updateContent(List<Modification> mods);

}