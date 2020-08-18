package com.self.learn.transaction.impl;


import com.self.learn.financemodel.base.Account;
import com.self.learn.transaction.base.BaseTransaction;

import java.math.BigDecimal;

public class Withdrawal extends BaseTransaction {

    public Withdrawal(Account account) {
        super(account);
    }

    public static Withdrawal from(Account account) {
        return new Withdrawal(account);
    }

    @Override
    public void commit(BigDecimal amount) {
        if (this.toAccount == null) {
            throw new IllegalArgumentException("Commit to which account?");
        }
        this.toAccount.takeAway(amount);
        this.metaData.setAmount(amount);
        this.trackable.update(this.getMetaData());
    }
}
