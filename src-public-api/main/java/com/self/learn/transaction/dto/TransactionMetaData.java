package com.self.learn.transaction.dto;

import com.self.learn.writer.adapter.TransactionAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;


@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionMetaData {

    @XmlElement(name = "id")
    private static final Integer id = new AtomicInteger(1).getAndIncrement();
    @XmlJavaTypeAdapter(TransactionAdapter.class)
    @XmlElement(name = "submitted-time")
    private LocalDate submittedTime = LocalDate.now();
    @XmlElement(name = "amount")
    private BigDecimal amount = BigDecimal.ONE;
    @XmlElement(name = "submitted-by")
    private String submittedBy = "Me";
    @XmlElement(name = "type-of-transaction")
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