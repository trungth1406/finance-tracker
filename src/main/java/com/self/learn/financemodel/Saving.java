package com.self.learn.financemodel;

import com.self.learn.financemodel.base.Account;
import com.self.learn.financemodel.base.IncomeRelated;

public class Saving extends IncomeRelated {


    private Saving(Account account) {
        super(account);
    }

    public static Saving from(Account account){
        return new Saving(account);
    }

}
