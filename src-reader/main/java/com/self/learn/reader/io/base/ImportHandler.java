package com.self.learn.reader.io.base;

import com.self.learn.reader.HttpResponseReader;

import java.io.InputStream;

/**
 *
 */
public abstract class ImportHandler implements IOHandler {

    protected Reader reader = new HttpResponseReader();

    /**
     * Default constructor
     */
    public ImportHandler() {
    }

    /**
     *
     */
    public abstract InputStream openStream();


}