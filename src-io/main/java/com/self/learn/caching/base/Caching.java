package com.self.learn.caching.base;

import com.self.learn.caching.state.Modification;

import java.util.*;

/**
 * 
 */
public interface Caching  {


    /**
     * @return
     */
    public Set<Modification> update();

}