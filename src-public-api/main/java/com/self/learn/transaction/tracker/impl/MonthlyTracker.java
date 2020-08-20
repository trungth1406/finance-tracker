package com.self.learn.transaction.tracker.impl;

import com.self.learn.file.base.Writer;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.impl.TransactionMetaData;


public class MonthlyTracker implements Trackable {

    private Writer writer;

    public MonthlyTracker(Writer writer){
        this.writer = writer;
    }

    public MonthlyTracker() {
    }

    @Override
    public void update(TransactionMetaData metaData) {
        writer.write(metaData);
    }
}
