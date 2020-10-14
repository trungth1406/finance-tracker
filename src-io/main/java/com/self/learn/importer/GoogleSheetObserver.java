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
    public void create(List<Line> lines) {
        List<List<Object>> sheetList = generateNewContentFrom(lines);
        try {
            String name = String.format("Tháng %d - %s", LocalDate.now().getMonthValue(), UUID.randomUUID().toString());
            String title = SheetService.newSpreadsheet(name);
            sheetService.append(sheetList, System.getProperty("sheet.id"),
                    String.format("Tháng %d", LocalDate.now().getMonthValue()), title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContent(List<Line> lines) {
        List<List<Object>> sheetList = new ArrayList<>();
        try {
            for (Line line : lines) {
                if (!(line.getModificationState() instanceof Unchanged))
                    sheetList.add(Arrays.asList(line.getModificationState().getContent()));
            }
            sheetService.append(sheetList, "12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk",
                    String.format("Tháng %d", LocalDate.now().getMonthValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContent(List<Line> mods, String range) {
        List<List<Object>> sheetList = new ArrayList<>();
        try {
            List<Object> contents = new ArrayList<>();
            mods.stream().forEach(modification -> {
                contents.add(modification.getContent());
            });
            sheetList.add(contents);
            sheetService.append(sheetList, "12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk",
                    String.format("Tháng %d", LocalDate.now().getMonthValue()), range);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
