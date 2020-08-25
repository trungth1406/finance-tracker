package com.self.learn.exporter.impl;

import com.self.learn.dto.TransactionDTO;
import com.self.learn.exporter.base.Exporter;

import java.util.List;

public class GoogleSheetExporter implements Exporter<List<TransactionDTO>> {


    @Override
    public void export(List<TransactionDTO> is) {
        is.stream().forEach(System.out::println);
    }


}
