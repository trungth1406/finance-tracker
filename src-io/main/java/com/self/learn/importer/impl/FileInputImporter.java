package com.self.learn.importer.impl;

import com.self.learn.dto.TransactionDTO;
import com.self.learn.importer.type.FileType;
import com.self.learn.importer.type.Type;
import com.self.learn.transaction.reader.Importer;
import com.self.learn.importer.base.BaseImporter;
import com.self.learn.exporter.base.Exporter;

import java.io.*;
import java.util.List;

/**
 *
 */
public class FileInputImporter extends BaseImporter<Object> implements Importer<InputStream> {

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
        toType.export(transformed);
    }
}