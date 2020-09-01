package com.self.learn.watcher.base;

import java.util.ArrayList;
import java.util.List;

public interface EventNotifier {

     List<EventObserver> eventObserver = new ArrayList<>();

     void addEventObserver(EventObserver eventObserver);

}
