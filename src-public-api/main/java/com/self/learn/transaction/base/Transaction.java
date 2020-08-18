package com.self.learn.transaction.base;

import java.math.BigDecimal;

public interface Transaction {

    void commit(BigDecimal amount);
}
