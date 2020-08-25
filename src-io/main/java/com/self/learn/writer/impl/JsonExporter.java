package com.self.learn.writer.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.self.learn.writer.base.Exporter;
import com.self.learn.transaction.dto.TransactionMetaData;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;


public class JsonExporter implements Exporter<InputStream> {


    private static Gson parser = new GsonBuilder().setPrettyPrinting().create();


    public JsonExporter() {
        
    }

    public void export(TransactionMetaData content) {
        File file = findFile();
        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            ArrayList<TransactionMetaData> types = setUpContent(reader, content);
            writeTo(file, types);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static File findFile() {
        Path path = Paths.get(String.format("daily-transaction-%s.json", LocalDate.now().toString()));
        File file = path.toFile();
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

    @Override
    public void export(InputStream is) {
        File file = findFile();
        try(JsonWriter jsonWriter = new JsonWriter( new FileWriter(file))){
            writeToFile(jsonWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginArray();
        jsonWriter.beginObject();
        jsonWriter.name("transaction");
        jsonWriter.endObject();
        jsonWriter.endArray();
    }
}