package com.self.learn.importer;

import com.self.learn.google.api.service.SheetService;
import com.self.learn.state.Line;
import com.self.learn.state.Unchanged;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class GoogleSheetObserver implements ContentObserver<List<Line>> {

    private final SheetService sheetService;

    public GoogleSheetObserver(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    private static List<List<Object>> generateContentFrom(List<Line> lines) {
        List<List<Object>> sheetList = new ArrayList<>();
        for (Line line : lines) {
            if (!(line.getModificationState() instanceof Unchanged))
                sheetList.add(Arrays.asList(line.getModificationState().getContent()));
        }
        return sheetList;
    }

    private static List<List<Object>> generateNewContentFrom(List<Line> lines) {
        List<List<Object>> sheetList = new ArrayList<>();
        for (Line line : lines) {
            sheetList.add(Arrays.asList(line.getModificationState().getContent()));
        }
        return sheetList;
    }

    // STOPSHIP: 10/14/20 Find a way to add new sheet to existing sheet
    @Override
    public void create(List<Line> lines,String sheetId) {
        List<List<Object>> sheetList = generateNewContentFrom(lines);
        try {
            String name = String.format("Tháng %d - %s", LocalDate.now().getMonthValue(),sheetId);
            SheetService.newSpreadsheet(name);
            sheetService.append(sheetList, "12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk",
                    name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // STOPSHIP: 10/19/20 refactor code and check for bug when changing content of the file
    @Override
    public void updateContent(List<Line> lines, String sheetId) {
        try {
            List<List<Object>> sheetList = generateContentFrom(lines);
            String name = String.format("Tháng %d - %s", LocalDate.now().getMonthValue(), sheetId);
            for(Line line : lines){
                sheetService
                        .update(sheetList, "12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk", name
                                , line.getModificationState().getRange('A'));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
