package com.self.learn.transaction.reader;

import java.io.InputStream;

/**
 * 
 */
public interface Importer {

    /**
     * @param stream
     */
    public void importFrom(InputStream stream);


}