package com.self.learn.reader.type.impl;

import com.self.learn.TransactionDTO;
import com.self.learn.reader.type.FileType;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TextFile implements FileType {


    @Override
    public String getType() {
        return ".txt";
    }

    @Override
    public List<TransactionDTO> transform(InputStream inputStream) {
        List<TransactionDTO> transformedDtos = new ArrayList<>();
        TransactionDTO dtoObject;
        Map<String, String> props;
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(inputStream))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.isEmpty()) continue;
                dtoObject = new TransactionDTO();
                props = new HashMap<>();
                dtoObject.setPerformedDate(extractDate(line));
                dtoObject.setProps(extractProperties(reader, props));
                transformedDtos.add(dtoObject);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transformedDtos;
    }


    private static LocalDate extractDate(String line) {
        String formatted = processDateFormat(line);
        LocalDate toDate = LocalDate.parse(formatted, DateTimeFormatter.ofPattern("d/MM/yyyy"));
        return toDate;
    }

    private static String processDateFormat(String line) {
        StringBuilder sb = new StringBuilder();
        String[] splitted = line.split("/");
        for (String s : splitted) {
            if (Integer.parseInt(s) < 10) {
                sb.append('0');
            }
            sb.append(s);
            sb.append("/");
        }
        sb.append(LocalDate.now().getYear());
        return sb.toString();
    }

    private static Map<String, String> extractProperties(LineNumberReader reader, Map<String, String> props) throws IOException {
        reader.mark(0);
        String newLine = reader.readLine();
        if (Objects.isNull(newLine) || newLine.isEmpty()) {
            return props;
        } else if (isLineDate(newLine)) {
            reader.reset();
            return props;
        }
        splitKeyAndValue(props, newLine);
        return extractProperties(reader, props);
    }

    private static boolean isLineDate(String line) {
        return Character.isDigit(line.charAt(0)) && line.contains("/");
    }

    private static void splitKeyAndValue(Map<String, String> props, String line) {
        char[] chars = line.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (isCharacter(chars[i])) {
                sb.append(chars[i]);
            }
        }
        props.put(sb.toString(), line.substring(sb.length()));
    }

    private static boolean isCharacter(char aChar) {
        return !Character.isDigit(aChar);
    }

}
