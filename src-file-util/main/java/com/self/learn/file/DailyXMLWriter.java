package com.self.learn.file;

import com.self.learn.file.base.BaseWriter;
import com.self.learn.transaction.impl.TransactionMetaData;

import java.time.LocalDate;
import java.util.ArrayList;

public class DailyXMLWriter extends BaseWriter<TransactionMetaData> {

    @Override
    protected Object bootstrapContent(TransactionMetaData content, ArrayList<TransactionMetaData> arr) {
        return null;
    }

    @Override
    public String fileName() {
        return String.format("daily-transaction-%s.xml", LocalDate.now().toString());
    }
}
