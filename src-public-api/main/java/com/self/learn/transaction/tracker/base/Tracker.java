package com.self.learn.transaction.tracker.base;

import com.self.learn.transaction.tracker.impl.DailyTracker;
import com.self.learn.transaction.tracker.impl.MonthlyTracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class Tracker implements Trackable {

    protected static List<Trackable> trackers = new ArrayList<>();

    public Tracker(List<Trackable> trackers) {
        this.trackers = trackers;
    }

    public Tracker() {
    }

    public void addTracker(Trackable... tracker) {
        trackers.addAll(Arrays.asList(tracker));
    }

}
