package com.self.learn.file;

import com.self.learn.file.base.BaseWriter;
import com.self.learn.transaction.impl.TransactionMetaData;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthlyJsonWriter  extends BaseWriter<TransactionMetaData> {

    @Override
    protected Object bootstrapContent(TransactionMetaData content, ArrayList<TransactionMetaData> arr) {
        System.out.println(fileName());
        return arr;
    }

    @Override
    public String fileName() {
        return String.format("%s-summary.json", LocalDate.now().getMonth());
    }
}
