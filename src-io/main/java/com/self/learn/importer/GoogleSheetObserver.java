package com.self.learn.importer;

import com.self.learn.google.api.service.SheetService;
import com.self.learn.state.Modification;
import com.self.learn.state.Unchanged;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleSheetObserver implements ContentObserver<List<Modification>> {

    private final SheetService sheetService;

    public GoogleSheetObserver(SheetService sheetService) {
        this.sheetService = sheetService;
    }

    @Override
    public void updateContent(InputStream in) {
        return;
    }

    @Override
    public void updateContent(List<Modification> mods) {
        List<List<Object>> sheetList = new ArrayList<>();
        try {
            for (Modification mod : mods) {
                if(!(mod instanceof Unchanged))
                    sheetList.add(Arrays.asList(mod.getContent()));
            }
            sheetService.append(sheetList, "12TqYhXjfbVDt6C8zUjyBbgUbGJmhNJ4Mly1Lgi8gsgk",
                    String.format("Tháng %d", LocalDate.now().getMonthValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContent(List<Modification> mods, String range) {
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
