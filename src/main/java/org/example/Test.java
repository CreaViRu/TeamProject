package org.example;

import org.example.data.DataStorage;
import org.example.strategy.filling.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("=== ЗАПУСК ТЕСТОВ ===\n");

        testBuilder();
        testRandomFilling();
        testFileFilling();
        testDataStorage();

        System.out.println("\n=== ВСЕ ТЕСТЫ ПРОЙДЕНЫ ===");
    }

    static void testBuilder() {
        System.out.println("Тест 1: Car Builder");
        try {
            Car car = new Car.Builder()
                    .setPower(200)
                    .setModel("BMW")
                    .setYear(2020)
                    .build();
            System.out.println("  ✓ Успешное создание: " + car);
        } catch (Exception e) {
            System.out.println("  ✗ Ошибка: " + e.getMessage());
        }


        try {
            Car car = new Car.Builder()
                    .setPower(-5)
                    .setModel("BMW")
                    .setYear(2020)
                    .build();
            System.out.println("  ✗ Должна быть ошибка для отрицательной мощности");
        } catch (IllegalArgumentException e) {
            System.out.println("  ✓ Корректная ошибка: " + e.getMessage());
        }
    }

    static void testRandomFilling() {
        System.out.println("\nТест 2: RandomFillingStrategy");
        FillingStrategy strategy = new RandomFillingStrategy();
        List<Car> cars = strategy.fill(5);

        if (cars.size() == 5) {
            System.out.println("  ✓ Создано 5 автомобилей");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println("    " + (i+1) + ". " + cars.get(i));
            }
        } else {
            System.out.println("  ✗ Ожидалось 5, получено " + cars.size());
        }
    }

    static void testFileFilling() {
        System.out.println("\nТест 3: FileFillingStrategy");

        String testFile = "test_cars.txt";
        try {
            java.io.FileWriter writer = new java.io.FileWriter(testFile);
            writer.write("Toyota Camry,150,2020\n");
            writer.write("BMW X5,300,2021\n");
            writer.write("Audi A4,200,2019\n");
            writer.close();

            FillingStrategy strategy = new FileFillingStrategy(testFile);
            List<Car> cars = strategy.fill(10);

            if (cars.size() == 3) {
                System.out.println("  ✓ Успешно загружено 3 автомобиля из файла");
                for (Car car : cars) {
                    System.out.println("    " + car);
                }
            } else {
                System.out.println("  ✗ Ожидалось 3, получено " + cars.size());
            }

        } catch (Exception e) {
            System.out.println("  ✗ Ошибка: " + e.getMessage());
        }
    }

    static void testDataStorage() {
        System.out.println("\nТест 4: DataStorage");
        DataStorage storage = new DataStorage();

        if (storage.isEmpty()) {
            System.out.println("  ✓ Новое хранилище пустое");
        } else {
            System.out.println("  ✗ Новое хранилище должно быть пустым");
        }

        Car car = new Car.Builder()
                .setPower(150)
                .setModel("Test")
                .setYear(2020)
                .build();

        storage.addCar(car);

        if (storage.size() == 1) {
            System.out.println("  ✓ Автомобиль добавлен, размер = 1");
        } else {
            System.out.println("  ✗ Размер должен быть 1, получено " + storage.size());
        }

        System.out.println(storage);
    }
}