package com.self.learn.reader.type;

import com.self.learn.reader.type.impl.Excel;
import com.self.learn.reader.type.impl.TextFile;

public enum Type {

    TEXT,
    XLSX,
    XLS,
    CSV;


    public FileType getFileType(Type type) {
        switch (type) {
            case TEXT:
                return new TextFile();
            case XLSX:
                return new Excel();
            default:
                throw new IllegalArgumentException("No such file type");
        }
    }
}
