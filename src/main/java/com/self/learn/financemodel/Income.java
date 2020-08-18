package com.self.learn.financemodel;

import com.self.learn.financemodel.base.Account;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Income implements Account {

    private BigDecimal totalAmount = BigDecimal.valueOf(0);

    @Override
    public void addToAccount(BigDecimal amount) {
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

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
