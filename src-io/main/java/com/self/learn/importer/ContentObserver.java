package com.self.learn.importer;

import java.io.InputStream;

/**
 *
 */
public interface ContentObserver<T> {

    /**
     * @param in
     */
    void updateContent(InputStream in);

    /**
     * @param mods
     */
    void updateContent(T mods);

    void updateContent(T mods, String range);

}