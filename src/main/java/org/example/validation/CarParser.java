package org.example.validation;

import org.example.data.model.Car;

public class CarParser {

    private final CarValidator validator;

    public CarParser() {
        this(CarValidator.standardValidator());
    }

    public CarParser(CarValidator validator) {
        this.validator = validator;
    }

    public Car parseFromCsv(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Неверный формат. Ожидается: Модель,Мощность,Год");
            }

            String model = parts[0].trim();
            int power = Integer.parseInt(parts[1].trim());
            int year = Integer.parseInt(parts[2].trim());

            Car car = new Car.Builder()
                    .setModel(model)
                    .setPower(power)
                    .setYear(year)
                    .build();

            validator.validate(car);
            return car;

        } catch (Exception e) {
            System.err.println("Ошибка парсинга: " + e.getMessage());
            return null;
        }
    }
}