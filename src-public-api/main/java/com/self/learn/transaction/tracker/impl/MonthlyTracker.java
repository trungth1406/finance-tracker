package com.self.learn.transaction.tracker.impl;

import com.self.learn.writer.base.Exporter;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.dto.TransactionMetaData;


public class MonthlyTracker implements Trackable {

    private Exporter exporter;

    public MonthlyTracker(Exporter exporter){
        this.exporter = exporter;
    }

    public MonthlyTracker() {
    }

    @Override
    public void update(TransactionMetaData metaData) {
        exporter.export(null);
    }
}
