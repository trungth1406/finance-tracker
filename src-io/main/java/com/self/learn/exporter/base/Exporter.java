package com.self.learn.exporter.base;

import java.io.OutputStream;

/**
 *
 */
public interface Exporter<T> {

    void export(T is);



}