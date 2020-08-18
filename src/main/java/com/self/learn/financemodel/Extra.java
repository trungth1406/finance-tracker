package com.self.learn.financemodel;

import com.self.learn.financemodel.base.Account;
import com.self.learn.financemodel.base.IncomeRelated;


public class Extra extends IncomeRelated {

    private Extra(Account account) {
        super(account);
    }

    public static Extra from(Account account){
        return new Extra(account);
    }


}
