package com.self.learn.importer.impl;

import com.self.learn.state.Modification;
import com.self.learn.importer.ContentObserver;
import com.self.learn.dto.TransactionDTO;
import com.self.learn.importer.type.FileType;
import com.self.learn.importer.type.Type;
import com.self.learn.transaction.reader.Importer;
import com.self.learn.importer.base.BaseImporter;
import com.self.learn.exporter.base.Exporter;

import java.io.*;
import java.util.List;

/**
 * TODO: Importer should be more flexible
 */
public class FileInputImporter extends BaseImporter<Object> implements Importer<InputStream>, ContentObserver {

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

    @Override
    public void updateContent(InputStream in) {
        List<TransactionDTO> transformed = fromType.transform(in);
        toType.export(transformed);
    }

    @Override
    public void updateContent(List<Modification> mods) {
        //TODO: Handle modifications from File
    }
}