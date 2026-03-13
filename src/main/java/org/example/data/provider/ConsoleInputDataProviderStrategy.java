package org.example.data.provider;

import org.example.util.CustomArrayList;
import org.example.util.InputValidator;

import java.util.Scanner;

public class ConsoleInputDataProviderStrategy<T> implements DataProviderStrategy<T> {

    private final Parser<T> parser;
    private final InputValidator inputValidator;

    public ConsoleInputDataProviderStrategy(Parser<T> parser) {
        this.parser = parser;
        this.inputValidator = new InputValidator(new Scanner(System.in));
    }

    @Override
    public CustomArrayList<T> provideData(int size) {
        CustomArrayList<T> data = new CustomArrayList<>();
        String model = inputValidator.readString("Введите название модели ", false);
        int wattage = inputValidator.readInt("Введите мощность [100-300] ", 100, 300);
        int productionYear = inputValidator.readInt("Введите год производства [1999 - 2026] ", 1999, 2026);
        StringBuilder sb = new StringBuilder();
        sb.append(model)
                .append(",")
                .append(wattage)
                .append(",")
                .append(productionYear);
        String sourceLine = sb.toString();
        try {
            data.add(parser.parse(sourceLine));
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
            return provideData(size);
        }

        return data;
    }
}