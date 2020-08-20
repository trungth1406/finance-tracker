package com.self.learn.file;

import com.google.gson.*;
import com.self.learn.file.base.BaseWriter;
import com.self.learn.transaction.impl.TransactionMetaData;

import java.time.LocalDate;
import java.util.ArrayList;


public class DailyJsonWriter extends BaseWriter<TransactionMetaData> {

    public DailyJsonWriter() {
    }

    @Override
    protected Object bootstrapContent(TransactionMetaData content, ArrayList<TransactionMetaData> arr) {
        arr.add(content);
        return arr;
    }

    @Override
    public String fileName() {
        return String.format("daily-transaction-%s.json", LocalDate.now().toString());
    }
}