package com.self.learn.transaction.tracker.impl;

import com.self.learn.file.JSONWriter;
import com.self.learn.file.base.Writer;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.impl.TransactionMetaData;

import java.time.LocalDate;

public class DailyTracker implements Trackable {

    private Writer writer = new JSONWriter();

    public DailyTracker(Writer writer){
        this.writer = writer;
    }

    public DailyTracker() {
    }

    @Override
    public void update(TransactionMetaData metaData) {
        this.writer.write(String.format("daily-transaction-%s.json", LocalDate.now().toString()),metaData);

    }
}
