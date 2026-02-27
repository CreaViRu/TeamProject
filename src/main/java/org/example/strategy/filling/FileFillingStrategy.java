package org.example.strategy.filling;

import org.example.data.model.Car;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFillingStrategy implements FillingStrategy {
    private final String filename;

    public FileFillingStrategy(String filename) {
        this.filename = filename;
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

                try {
                    Car car = parseCarFromLine(line);
                    if (car != null) {
                        cars.add(car);
                        System.out.println("  Загружен: " + car);
                    }
                } catch (Exception e) {
                    System.out.println("  Ошибка в строке " + lineNumber + ": " + e.getMessage());
                }
            }

            System.out.println("Загружено " + cars.size() + " автомобилей из файла");

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return cars;
    }

    private Car parseCarFromLine(String line) {

        String[] parts = line.split(",");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки. Ожидается: Модель,Мощность,Год");
        }

        String model = parts[0].trim();
        int power = Integer.parseInt(parts[1].trim());
        int year = Integer.parseInt(parts[2].trim());

        return new Car.Builder()
                .setModel(model)
                .setPower(power)
                .setYear(year)
                .build();
    }
}