package com.self.learn.transaction.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "transactions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transactions {

    @XmlElement(name = "transaction")
    private List<TransactionMetaData> transactionMetaDataList = new ArrayList<>();

    public List<TransactionMetaData> getTransactionMetaDataList() {
        return transactionMetaDataList;
    }

}
