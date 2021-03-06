package com.self.learn.exporter.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public abstract class BaseJsonExporter<T> implements Exporter {

    protected static Gson parser = new GsonBuilder().setPrettyPrinting().create();

//    @Override
//    public void export(T content) {
//        File file = new File(setUpFileName());
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (JsonReader reader = new JsonReader(new FileReader(file))) {
//            ArrayList<Object> types = generateTypeArray(reader);
//            try (Writer writer = new FileWriter(file.getAbsolutePath());) {
//                parser.toJson(bootstrapContent(content, types), writer);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private ArrayList<Object> generateTypeArray(JsonReader reader) {
        Type userListType = new TypeToken<ArrayList<Object>>() {
        }.getType();
        ArrayList<Object> array = parser.fromJson(reader, userListType);
        return Objects.isNull(array) ? new ArrayList<>() : array;
    }

    protected abstract Object bootstrapContent(T content, ArrayList<Object> arr);

    protected abstract String setUpFileName();
}
