package com.self.learn.reader.type;

import com.self.learn.TransactionDTO;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;

public interface FileType {


    String getType();

    List<TransactionDTO> transform(InputStream stream);


}
