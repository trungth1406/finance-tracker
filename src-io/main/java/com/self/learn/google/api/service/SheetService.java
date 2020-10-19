package com.self.learn.google.api.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.self.learn.google.api.config.GoogleSheetConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface SheetService {


    static Sheets getSheetService() {
        return GoogleSheetConfiguration.getInstance("google-sheet.properties").getSheetService();
    }

    static void newSpreadsheet(String title) throws IOException {
        AddSheetRequest request = new AddSheetRequest();
        request.setProperties(new SheetProperties().setTitle(title));
        BatchUpdateSpreadsheetRequest batchUpdate = new BatchUpdateSpreadsheetRequest();
        batchUpdate.setRequests(Arrays.asList(new Request().setAddSheet(request)));
        BatchUpdateSpreadsheetResponse update = getSheetService()
                .spreadsheets()
                .batchUpdate(
                        "12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk",
                        batchUpdate)
                .execute();
    }

    List<List<Object>> read(String spreadSheetId, String sheetId, String range) throws IOException;

    void   append(List<List<Object>> values, String spreadSheetId, String sheetId) throws IOException;

    void update(List<List<Object>> values, String spreadSheetId, String sheetId, String range) throws IOException;

    void appendRanges(List<List<Object>> values, String spreadSheetId, String sheetId, String range) throws IOException;

    void delete(String spreadSheetId, String sheetId, String range);

}
