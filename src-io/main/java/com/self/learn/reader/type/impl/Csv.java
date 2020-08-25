package com.self.learn.reader.type.impl;

import com.self.learn.dto.TransactionDTO;
import com.self.learn.reader.type.FileType;

import java.io.*;
import java.util.List;

public class Csv implements FileType {

    @Override
    public String getType() {
        return null;
    }

    @Override
    public List<TransactionDTO> transform(InputStream stream) {
        return null;
    }


}
