package com.self.learn.writer.impl;

import com.self.learn.TransactionDTO;
import com.self.learn.writer.base.Exporter;

import java.util.List;

public class GoogleSheetExporter implements Exporter<List<TransactionDTO>> {


    @Override
    public void export(List<TransactionDTO> is) {
        is.stream().forEach(System.out::println);
    }


}
