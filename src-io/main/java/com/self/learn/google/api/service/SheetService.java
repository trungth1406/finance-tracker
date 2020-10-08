package com.self.learn.google.api.service;

import com.google.api.services.sheets.v4.Sheets;
import com.self.learn.google.api.config.GoogleSheetConfiguration;

import java.io.IOException;
import java.util.List;

public interface SheetService {


    static Sheets getSheetService() {
        return GoogleSheetConfiguration.getInstance("google-sheet.properties").getSheetService();
    }

    List<List<Object>> read(String spreadSheetId, String sheetId, String range) throws IOException;

    void append(List<List<Object>> values, String spreadSheetId, String sheetId) throws IOException;

    void append(List<List<Object>> values, String spreadSheetId, String sheetId, String range) throws IOException;

    void appendRanges(List<List<Object>> values, String spreadSheetId, String sheetId, String range) throws IOException;

    void delete(String spreadSheetId, String sheetId, String range);

}
