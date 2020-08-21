package com.self.learn.transaction.tracker.impl;

import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.tracker.base.Tracker;
import com.self.learn.transaction.dto.TransactionMetaData;

import java.util.List;


public class CompositeTracker extends Tracker {

    public CompositeTracker(List<Trackable> trackers) {
        super(trackers);
    }

    public CompositeTracker() {
    }

    @Override
    public void update(TransactionMetaData metaData) {
        trackers.stream().forEach(trackable -> trackable.update(metaData));
    }
}
