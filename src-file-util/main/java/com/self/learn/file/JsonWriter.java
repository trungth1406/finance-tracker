package com.self.learn.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.self.learn.file.base.Writer;
import com.self.learn.transaction.dto.TransactionMetaData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class JsonWriter implements Writer<TransactionMetaData> {


    private static Gson parser = new GsonBuilder().setPrettyPrinting().create();

    private Writer<?> nextWriter;

    public JsonWriter() {
        
    }

    public void write(TransactionMetaData content) {
        File file = setUpFile();
        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            ArrayList<TransactionMetaData> types = setUpContent(reader, content);
            writeTo(file, types);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File setUpFile() {
        File file = new File(String.format("daily-transaction-%s.json", LocalDate.now().toString()));
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static ArrayList<TransactionMetaData> setUpContent(JsonReader reader, TransactionMetaData content) {
        Type userListType = new TypeToken<ArrayList<Object>>() {
        }.getType();
        ArrayList<TransactionMetaData> array = parser.fromJson(reader, userListType);
        array = Objects.isNull(array) ? new ArrayList<>() : array;
        array.add(content);
        return array;
    }

    private static void writeTo(File file, ArrayList<TransactionMetaData> types) throws IOException {
        try (java.io.Writer writer = new FileWriter(file.getAbsolutePath());) {
            parser.toJson(types, writer);
        }
    }


}