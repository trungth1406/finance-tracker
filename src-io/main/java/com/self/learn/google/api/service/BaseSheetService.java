package com.self.learn.google.api.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseSheetService implements SheetService {


    private Sheets sheetService = SheetService.getSheetService();

    @Override
    public List<List<Object>> read(String spreadSheetId, String sheetId, String range) throws IOException {
        Sheets sheetService = SheetService.getSheetService();
        ValueRange valueRange = sheetService.
                spreadsheets().values().
                get(spreadSheetId, String.format("%s!%s", sheetId, range)).
                execute();
        if (valueRange.getValues() == null || valueRange.isEmpty()) {
            return Collections.emptyList();
        }
        return valueRange.getValues();
    }

    @Override
    public void append(List<List<Object>> values, String spreadSheetId, String sheetId) throws IOException {
        ValueRange body = new ValueRange().setValues(values);
        sheetService.spreadsheets().values().
                append(spreadSheetId, sheetId, body).
                setValueInputOption("USER_ENTERED").
                setIncludeValuesInResponse(true).
                execute();
    }

    // STOPSHIP: 10/7/20 Check for document of appending sheet ID
    @Override
    public void update(List<List<Object>> values, String spreadSheetId, String sheetId, String range) throws IOException {
        String formatted = String.format("%s!%s", sheetId, range);
        ValueRange body = new ValueRange().setValues(values);
        sheetService.spreadsheets().values().
                update(spreadSheetId, formatted, body).
                setValueInputOption("RAW").
                setIncludeValuesInResponse(true).
                execute();
    }


    @Override
    public void appendRanges(List<List<Object>> values, String spreadSheetId, String sheetId, String range) throws IOException {
        String formatted = String.format("%s!%s", sheetId, range);
        List<ValueRange> data = new ArrayList<>();
        data.add(new ValueRange()
                .setRange(range)
                .setValues(values));
        ValueRange body = new ValueRange().setRange(formatted).setValues(values);
        sheetService.spreadsheets().values().
                update(spreadSheetId, sheetId, body).
                setValueInputOption("USER_ENTERED").
                setIncludeValuesInResponse(true).
                execute();
    }

    @Override
    public void delete(String spreadSheetId, String sheetId, String range) {
        //TODO: To be implemented
    }
}
