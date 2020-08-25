package com.self.learn.exporter.impl;

import com.self.learn.exporter.base.Exporter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeJsonExporter implements Exporter<InputStream> {

    private List<Exporter> exporters;

    public void setChildrenWriter(Exporter... exporters) {
        this.exporters = new ArrayList<>(Arrays.asList(exporters));
    }

    @Override
    public void export(InputStream is) {

    }
}
