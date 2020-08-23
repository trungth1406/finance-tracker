package com.self.learn.reader.type.impl;

import com.self.learn.TransactionDTO;
import com.self.learn.reader.type.FileType;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;

public class Excel implements FileType {


    @Override
    public List<TransactionDTO> transform(InputStream stream) {
        return null;
    }

    @Override
    public String getType() {
        return ".xlsx";
    }
}
