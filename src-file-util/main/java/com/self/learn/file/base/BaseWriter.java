package com.self.learn.file.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.Writer;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class BaseWriter<T> implements com.self.learn.file.base.Writer<T> {

    protected static Gson parser = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void write(T content) {
        File file = new File(fileName());
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            ArrayList<T> types = extractTypesWith(reader);
            try (Writer writer = new FileWriter(file.getAbsolutePath());) {
                parser.toJson(bootstrapContent(content, types), writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private ArrayList<T> extractTypesWith(JsonReader reader) {
        Type userListType = new TypeToken<ArrayList<T>>() {}.getType();
        ArrayList<T> array = parser.fromJson(reader, userListType);
        return array == null ? new ArrayList<>() : array;
    }

    protected abstract Object bootstrapContent(T content, ArrayList<T> arr);
}
