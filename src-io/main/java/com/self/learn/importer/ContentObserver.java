package com.self.learn.importer;

import java.io.InputStream;

/**
 *
 */
public interface ContentObserver<T> {


    void create(T lines);

    void updateContent(T mods);

    void updateContent(T mods, String range);

}