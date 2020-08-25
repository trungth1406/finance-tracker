package com.self.learn.writer.base;

import org.apache.poi.ss.formula.functions.T;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 
 */
public interface Exporter<T> {

    void export(T is);



}