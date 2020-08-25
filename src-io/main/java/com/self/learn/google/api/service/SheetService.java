package com.self.learn.google.api.service;

import com.self.learn.google.api.GoogleSheetConfiguration;

import java.util.List;

public interface SheetService {

    GoogleSheetConfiguration config = GoogleSheetConfiguration.setUp("google-sheet.properties");

    List<List<Object>> read(String range);

    void create(List<List<Object>> values, String range);

    void delete(String range);

}
