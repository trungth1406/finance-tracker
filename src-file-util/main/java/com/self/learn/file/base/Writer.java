package com.self.learn.file.base;

import java.io.File;
import java.util.*;

/**
 * 
 */
public interface Writer<T> {

    public void write(String fileName,T content);

}