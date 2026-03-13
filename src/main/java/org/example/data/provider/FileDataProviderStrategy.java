package org.example.data.provider;

import org.example.util.CustomArrayList;

import java.io.BufferedReader;
import java.io.IOException;

public class FileDataProviderStrategy<T> implements DataProviderStrategy<T> {

    private final String filePath;
    private final Parser<T> parser;

    public FileDataProviderStrategy(String filePath, Parser<T> parser) {
        this.filePath = filePath;
        this.parser = parser;
    }

    @Override
    public CustomArrayList<T> provideData(int size) {
        CustomArrayList<T> data = new CustomArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filePath));
            String line;
            int linesCount = 0;
            while ((line = bufferedReader.readLine()) != null && linesCount < size) {
                data.add(parser.parse(line));
                linesCount++;
            }
        } catch (IOException e) {
            System.out.println("This file doesn't exist, try again");
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
}
