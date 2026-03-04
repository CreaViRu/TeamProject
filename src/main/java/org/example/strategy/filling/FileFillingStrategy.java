package org.example.strategy.filling;

import org.example.data.model.Car;
import org.example.validation.CarParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFillingStrategy implements FillingStrategy {

    private final String filename;
    private final CarParser parser;

    public FileFillingStrategy(String filename) {
        this.filename = filename;
        this.parser = new CarParser();
    }

    @Override
    public List<Car> fill(int size) {
        List<Car> cars = new ArrayList<>();
        int lineNumber = 0;

        System.out.println("Чтение файла: " + filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null && cars.size() < size) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                Car car = parser.parseFromCsv(line);
                if (car != null) {
                    cars.add(car);
                    System.out.println("  Загружен: " + car);
                } else {
                    System.out.println("  Ошибка в строке " + lineNumber);
                }
            }

            System.out.println("Загружено " + cars.size() + " автомобилей из файла");

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return cars;
    }
}