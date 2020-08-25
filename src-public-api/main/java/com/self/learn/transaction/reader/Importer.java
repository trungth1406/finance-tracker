package com.self.learn.transaction.reader;

import java.io.InputStream;

/**
 * 
 */
public interface Importer<T> {

    /**
     * @param stream
     */
    public void importFrom(T stream);


}