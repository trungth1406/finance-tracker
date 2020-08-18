package com.self.learn.transaction.base;


import com.self.learn.financemodel.base.Account;
import com.self.learn.transaction.tracker.base.Trackable;
import com.self.learn.transaction.tracker.impl.CompositeTracker;
import com.self.learn.transaction.impl.TransactionMetaData;


public abstract class BaseTransaction implements Transaction {

    protected Trackable trackable;
    protected Account toAccount;
    protected TransactionMetaData metaData = new TransactionMetaData();

    public BaseTransaction(Account toAccount) {
        this.toAccount = toAccount;
        this.trackable = new CompositeTracker();
        this.metaData.setTypeOfTransaction(this.getClass().getSimpleName());
    }

    public TransactionMetaData getMetaData() {
        return this.metaData;
    }
}
