package com.self.learn.transaction.impl;

import com.google.gson.JsonElement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionMetaData {


    private static Integer id = new AtomicInteger().getAndIncrement();
    private LocalDate submittedTime = LocalDate.now();
    private BigDecimal amount = BigDecimal.ONE;
    private String submittedBy = "Me";
    private String typeOfTransaction;

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public LocalDate getSubmittedTime() {
        return submittedTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

}