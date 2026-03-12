package org.example.data.provider;

import org.example.util.CustomArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInputDataProviderStrategy<T> implements DataProviderStrategy<T> {

    private final Parser<T> parser;
    private final String consoleMessage;

    public ConsoleInputDataProviderStrategy(Parser<T> parser, String consoleMessage) {
        this.parser = parser;
        this.consoleMessage = consoleMessage;
    }

    @Override
    public CustomArrayList<T> provideData(int size) {
        CustomArrayList<T> data = new CustomArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println(consoleMessage);
        String sourceLine = scanner.nextLine();

        try {
            data.add(parser.parse(sourceLine));
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return provideData(size);
        }

        return data;
    }
}