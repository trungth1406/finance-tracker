package com.self.learn.transaction.impl;


import com.self.learn.financemodel.base.Account;
import com.self.learn.transaction.base.BaseTransaction;

import java.math.BigDecimal;

public class Deposit extends BaseTransaction {

    public Deposit(Account toAccount) {
        super(toAccount);
    }

    @Override
    public void commit(BigDecimal amount) {
        this.toAccount.addToAccount(amount);
        this.metaData.setAmount(amount);
        this.trackable.update(this.getMetaData());
    }
}
