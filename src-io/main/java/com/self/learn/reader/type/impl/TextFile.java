package com.self.learn.reader.type.impl;

import com.self.learn.dto.TransactionDTO;
import com.self.learn.reader.type.FileType;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextFile implements FileType {


    @Override
    public String getType() {
        return ".txt";
    }

    @Override
    public List<TransactionDTO> transform(InputStream inputStream) {
        List<TransactionDTO> transformedDtos = new ArrayList<>();
        TransactionDTO dtoObject;
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(inputStream))) {
            Map<String, String> props;
            String line = reader.readLine();
            while (line != null) {
                dtoObject = new TransactionDTO();
                props = new HashMap<>();
                if (!isNotDate(line)) {
                    dtoObject.setPerformedDate(extractDate(line));
                }
                while (isNotDate(line) || line.isEmpty()) {
                    handleProperty(line, props);
                    line = reader.readLine();
                }
                transformedDtos.add(dtoObject);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transformedDtos;
    }

    private static boolean isNotDate(String line) {
        char ch = line.charAt(0);
        return Character.isAlphabetic(ch) || !Character.isLetterOrDigit(ch);
    }

    private static LocalDate extractDate(String line) {
        String formatted = processDateFormat(line);
        LocalDate toDate = LocalDate.parse(formatted, DateTimeFormatter.ofPattern("d/MM/yyyy"));
        return toDate;
    }

    private static String processDateFormat(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i)) && Integer.parseInt(String.valueOf(line.charAt(i))) < 10) {
                sb.append('0');

            }
            sb.append(line.charAt(i));
        }
        sb.append("/");
        sb.append(LocalDate.now().getYear());
        return sb.toString();
    }

    private static void handleProperty(String line, Map<String, String> props) {
        int endOfKey = endOfPropName(line);
        props.put(extractValue(line, 0, endOfKey + 1), extractValue(line, endOfKey + 1, line.length()));
    }

    private static String extractValue(String line, int start, int end) {
        return line.subSequence(start, end).toString();
    }

    private static int endOfPropName(String line) {
        for (int i = line.length() - 1; i >= 0; i--) {
            if (Character.isAlphabetic(line.charAt(i))) {
                return i;
            }
        }
        return -1;
    }


}
