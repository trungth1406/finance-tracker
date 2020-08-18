package com.self.learn.financemodel.base;

import java.math.BigDecimal;

public interface Account {

    void addToAccount(BigDecimal amount);

    void takeAway(BigDecimal amount);

    String getRemainingSum();

}
