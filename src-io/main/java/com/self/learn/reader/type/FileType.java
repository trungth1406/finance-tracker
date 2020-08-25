package com.self.learn.reader.type;

import com.self.learn.dto.TransactionDTO;

import java.io.InputStream;
import java.util.List;

public interface FileType {


    String getType();

    List<TransactionDTO> transform(InputStream stream);


}
