package com.self.learn.file.base;

import com.self.learn.transaction.impl.TransactionMetaData;

import java.io.File;
import java.util.*;

/**
 * 
 */
public interface Writer<T> {

    public void write(T content);

    String fileName();

}