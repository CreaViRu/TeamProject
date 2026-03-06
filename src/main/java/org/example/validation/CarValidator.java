package org.example.validation;

import org.example.data.model.Car;

import java.time.Year;

@FunctionalInterface
public interface CarValidator {

    void validate(Car car) throws IllegalArgumentException;

    default CarValidator andThen(CarValidator other) {
        return car -> {
            this.validate(car);
            other.validate(car);
        };
    }

    static CarValidator standardValidator() {
        return car -> {
            validateModel(car.getModel());
            validatePower(car.getPower());
            validateYear(car.getYear());
        };
    }

    private static void validateModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Модель не может быть пустой");
        }
        if (model.length() > 50) {
            throw new IllegalArgumentException("Модель слишком длинная (макс. 50 символов)");
        }
    }

    private static void validatePower(int power) {
        if (power <= 0 || power > 2000) {
            throw new IllegalArgumentException("Мощность должна быть от 1 до 2000 л.с.");
        }
    }

    private static void validateYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1886 || year > currentYear + 1) {
            throw new IllegalArgumentException(
                    "Год выпуска должен быть от 1886 до " + (currentYear + 1));
        }
    }
}