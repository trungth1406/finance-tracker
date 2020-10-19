package com.self.learn.importer;

import java.io.InputStream;

/**
 *
 */
public interface ContentObserver<T> {


    void create(T lines, String sheetId);


    void updateContent(T mods, String sheetId);

}