package com.self.learn.file;

import com.self.learn.file.base.BaseWriter;
import com.self.learn.file.base.Writer;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeJsonWriter implements Writer {

    private List<Writer> writers;

    public void setChildrenWriter(Writer... writers) {
        this.writers = new ArrayList<>(Arrays.asList(writers));
    }

    @Override
    public void write( Object content) {
        if (writers == null) throw new IllegalArgumentException("Add children writer to this writer");
        writers.stream().forEach(writer -> writer.write(content));
    }

    @Override
    public String fileName() {
        return "null";
    }
}
