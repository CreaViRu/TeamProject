package org.example.data.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataProviderStrategy<T> implements DataProviderStrategy<T> {

    private final String filePath;
    private final Parser<T> parser;
    private final String consoleMessage;

    public FileDataProviderStrategy(String filePath, Parser<T> parser, String consoleMessage) {
        this.filePath = filePath;
        this.parser = parser;
        this.consoleMessage = consoleMessage;
    }

    @Override
    public List<T> provideData(int size) {
        ArrayList<T> data = new ArrayList<>();
        try {
            System.out.println(consoleMessage);
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.add(parser.parse(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
        return data.subList(0, size);
    }
}
