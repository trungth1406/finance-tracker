package com.self.learn.exporter.impl;

import com.self.learn.dto.TransactionDTO;
import com.self.learn.exporter.base.Exporter;
import com.self.learn.google.api.config.GoogleSheetConfiguration;
import com.self.learn.google.api.service.BaseSheetService;
import com.self.learn.google.api.service.SheetService;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class GoogleSheetExporter implements Exporter<List<TransactionDTO>> {

    private static SheetService sheetService = new BaseSheetService();
    private static Properties propFile;

    public GoogleSheetExporter() {
        try (InputStream is = GoogleSheetConfiguration.class.getClassLoader().getResourceAsStream("google-sheet.properties")) {
            propFile = new Properties();
            propFile.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void export(List<TransactionDTO> inputStream) {
        List<List<Object>> rangeValues = convertToValueRanges(inputStream);
        exportToGoogleSheet(rangeValues);
    }

    private static List<List<Object>> convertToValueRanges(List<TransactionDTO> is) {
        List<List<Object>> rangeValues = new ArrayList<>();
        List<Object> rowValues;
        boolean containsDate = true;
        for (int i = 0; i < is.size(); i++) {
            for (Map.Entry entry : is.get(i).getProps().entrySet()) {
                rowValues = new ArrayList<>();
                if (containsDate) {
                    rowValues.add(formatDate(is.get(i).getPerformedDate()));
                    containsDate = false;
                } else {
                    rowValues.add("");
                }
                rowValues.add(entry.getKey());
                rowValues.add("");
                rowValues.add(entry.getValue());
                rangeValues.add(rowValues);
            }
            containsDate = true;
        }
        return rangeValues;
    }

    private static void exportToGoogleSheet(List<List<Object>> rangeValues) {
        try {
            sheetService.append(
                    rangeValues,
                    propFile.getProperty("sheet.id"),
                    String.format("Tháng %d", LocalDate.now().getMonthValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM"));
    }
}
