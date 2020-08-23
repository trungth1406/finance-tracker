package com.self.learn.transaction.tracker.impl;

import com.self.learn.writer.base.Exporter;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.dto.TransactionMetaData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyTracker implements Trackable {

    private List<Exporter> exporters;

    public DailyTracker(Exporter... exporter) {
        this.exporters = new ArrayList<>(Arrays.asList(exporter));
    }

    @Override
    public void update(TransactionMetaData metaData) {
        this.exporters.stream().forEach(writer ->
                writer.export(null));
    }

}
