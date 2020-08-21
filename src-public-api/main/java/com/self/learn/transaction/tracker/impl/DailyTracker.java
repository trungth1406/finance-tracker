package com.self.learn.transaction.tracker.impl;

import com.self.learn.file.base.Writer;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.dto.TransactionMetaData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyTracker implements Trackable {

    private List<Writer> writers;

    public DailyTracker(Writer... writer) {
        this.writers = new ArrayList<>(Arrays.asList(writer));
    }

    @Override
    public void update(TransactionMetaData metaData) {
        this.writers.stream().forEach(writer ->
                writer.write(metaData));
    }

}
