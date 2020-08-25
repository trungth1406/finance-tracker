package com.self.learn.importer.type.impl;

import com.self.learn.dto.TransactionDTO;
import com.self.learn.importer.type.FileType;

import java.io.InputStream;
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
