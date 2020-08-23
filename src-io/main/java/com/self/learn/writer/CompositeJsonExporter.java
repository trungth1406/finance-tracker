package com.self.learn.writer;

import com.self.learn.writer.base.Exporter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeJsonExporter implements Exporter {

    private List<Exporter> exporters;

    public void setChildrenWriter(Exporter... exporters) {
        this.exporters = new ArrayList<>(Arrays.asList(exporters));
    }

//    @Override
//    public void export(Object content) {
//        if (exporters == null) throw new IllegalArgumentException("Add children writer to this writer");
//        exporters.stream().forEach(writer -> writer.export(null));
//    }
//
//    @Override
//    public void write(InputStream oos) {
//        if (exporters == null) throw new IllegalArgumentException("Add children writer to this writer");
//        exporters.stream().forEach(writer -> writer.export(null));
//
//    }


    @Override
    public void export(InputStream is) {

    }
}
