package com.self.learn.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.self.learn.file.base.BaseWriter;
import com.self.learn.transaction.impl.TransactionMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


public class JSONWriter extends BaseWriter<TransactionMetaData> {

    /**
     * Default constructor
     */

    private static Gson parser = new GsonBuilder().setPrettyPrinting().create();

    public JSONWriter() {
    }

    private static Logger logger = LoggerFactory.getLogger(JSONWriter.class);


    @Override
    public void write(String fileName, TransactionMetaData content) {

        try (JsonReader reader = new JsonReader(new FileReader(fileName));
             Writer writer = new FileWriter(new File(fileName).getAbsolutePath(), true);) {
            if(reader.isLenient()){
                reader.beginArray();
                Type map = new TypeToken<Map<String, String>>(){}.getType();
                Map<String, Object> existingJson = parser.fromJson(reader, map);
                existingJson.put("type", content);
                reader.endArray();
                writer.write(parser.toJson(existingJson));
            }else{
                JsonArray array = new JsonArray();
                array.add(parser.toJson(content));
                writer.write(parser.toJson(array));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}