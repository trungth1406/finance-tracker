package com.self.learn.financemodel;

import com.self.learn.financemodel.base.Account;
import com.self.learn.financemodel.base.IncomeRelated;

public class LivingCost extends IncomeRelated {

    private LivingCost(Account account) {
        super(account);
    }

    public static LivingCost takeFrom(Account account){
        return new LivingCost(account);
    }

}
