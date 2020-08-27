package com.self.learn.caching;

import java.io.InputStream;
import java.util.*;

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