package com.self.learn.financemodel.base;

import java.math.BigDecimal;
import java.text.NumberFormat;

public abstract class IncomeRelated implements Account {

    protected BigDecimal totalAmount = BigDecimal.ZERO;
    protected Account account;

    public IncomeRelated(Account account) {
        this.account = account;
    }

    @Override
    public void addToAccount(BigDecimal amount) {
        this.account.takeAway(amount);
        this.totalAmount = this.totalAmount.add(amount);
    }

    @Override
    public void takeAway(BigDecimal amount) {
        this.totalAmount = this.totalAmount.subtract(amount);
    }

    @Override
    public String getRemainingSum() {
        return NumberFormat.getCurrencyInstance().format(this.totalAmount);
    }
}
