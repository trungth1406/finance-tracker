package com.self.learn.controller;


import com.self.learn.financemodel.Income;
import com.self.learn.financemodel.LivingCost;
import com.self.learn.financemodel.Saving;
import com.self.learn.transaction.base.impl.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("main/")
public class MainController {

    @Autowired
    private Income income;

    @Autowired
    private LivingCost livingCost;

    @Autowired
    private Saving saving;

    @RequestMapping(value = "/test")
    public ResponseEntity<Object> testBean() {
        System.out.println(livingCost.getRemainingSum());
        livingCost.addToAccount(BigDecimal.valueOf(1000000));
        Withdrawal withdrawal = Withdrawal.from(livingCost);
        withdrawal.commit(BigDecimal.valueOf(100000));
        return new ResponseEntity(income.getRemainingSum(),HttpStatus.OK);
    }



}
