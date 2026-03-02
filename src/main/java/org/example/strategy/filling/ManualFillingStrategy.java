package org.example.strategy.filling;

import org.example.data.model.Car;
import org.example.util.InputValidator;
import org.example.validation.CarValidator;
import java.util.ArrayList;
import java.util.List;

public class ManualFillingStrategy implements FillingStrategy {

    private final InputValidator validator;
    private final CarValidator carValidator;

    public ManualFillingStrategy(InputValidator validator) {
        this.validator = validator;
        this.carValidator = CarValidator.standardValidator();
    }

    public ManualFillingStrategy(InputValidator validator, CarValidator carValidator) {
        this.validator = validator;
        this.carValidator = carValidator;
    }

    @Override
    public List<Car> fill(int size) {
        List<Car> cars = new ArrayList<>();

        System.out.println("\n --- Ввод данных для " + size + " автомобилей ---");

        for (int i = 0; i < size; i++) {
            System.out.println("\nАвтомобиль #" + (i + 1));

            while (true) {
                try {
                    String model = validator.readString("Модель: ", false);
                    int power = validator.readInt("Мощность (л.с.) [1-2000]: ", 1, 2000);
                    int year = validator.readInt("Год выпуска [1886-2025]: ", 1886, 2025);

                    Car car = new Car.Builder()
                            .setModel(model)
                            .setPower(power)
                            .setYear(year)
                            .build();

                    // ВАЛИДАЦИЯ
                    carValidator.validate(car);

                    cars.add(car);
                    break;

                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка валидации: " + e.getMessage());
                    System.out.println("Попробуйте снова:");
                }
            }
        }

        System.out.println("\nУспешно введено " + size + " автомобилей");
        return cars;
    }
}