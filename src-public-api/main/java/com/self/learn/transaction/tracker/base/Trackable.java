package com.self.learn.transaction.tracker.base;


import com.self.learn.transaction.impl.TransactionMetaData;

public interface Trackable {

    void update(TransactionMetaData metaData);


}
