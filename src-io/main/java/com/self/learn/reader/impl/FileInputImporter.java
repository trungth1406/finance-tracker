package com.self.learn.reader.impl;

import com.self.learn.TransactionDTO;
import com.self.learn.reader.type.FileType;
import com.self.learn.reader.type.Type;
import com.self.learn.transaction.reader.Importer;
import com.self.learn.reader.base.BaseImporter;
import com.self.learn.writer.base.Exporter;

import java.io.*;
import java.util.List;

/**
 *
 */
public class FileInputImporter extends BaseImporter<Object> implements Importer {

    private FileType fromType;
    private Exporter toType;

    public FileInputImporter(Type fromType, Exporter toType) {
        this.fromType = fromType.getFileType(fromType);
        this.toType = toType;
    }

    /**
     * @param stream
     */
    public void importFrom(InputStream stream) {
        List<TransactionDTO> transformed = fromType.transform(stream);
        PipedInputStream in = new PipedInputStream();
//        toType.export(transformed);
    }
}