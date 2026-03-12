package org.example.menu.actions;

import org.example.data.DataStorage;
import org.example.data.model.Car;
import org.example.sorting.BubbleSortStrategy;
import org.example.sorting.NaturalBubbleSortForEvenStrategy;
import org.example.util.InputValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.function.ToIntFunction;

public class MenuActionHandler {
    private final DataStorage dataStorage;
    private final InputValidator inputValidator;

    public MenuActionHandler(DataStorage dataStorage, InputValidator inputValidator) {
        this.dataStorage = dataStorage;
        this.inputValidator = inputValidator;
    }

    public void handleRandomFill() {
        System.out.println("\n--- Случайное заполнение ---");
        int size = inputValidator.readInt("Введите количество автомобилей [1-100]: ", 1, 100);
//        FillingStrategy strategy = new RandomFillingStrategy();
//        dataStorage.setCars(strategy.fill(size));
        System.out.println("Текущее количество автомобилей: " + dataStorage.size());
    }

    public void handleManualFill() {
        System.out.println("\n--- Ручное заполнение ---");
        int size = inputValidator.readInt("Введите количество автомобилей [1-20]: ", 1, 20);
//        FillingStrategy strategy = new ManualFillingStrategy(inputValidator);
//        dataStorage.setCars(strategy.fill(size));
        System.out.println("Текущее количество автомобилей: " + dataStorage.size());
    }

    public void handleFileFill() {
        System.out.println("\n--- Заполнение из файла ---");
        String filename = inputValidator.readString("Введите имя файла: ", false);
        int maxSize = inputValidator.readInt("Максимальное количество для загрузки [1-100]: ", 1, 100);
//        FillingStrategy strategy = new FileFillingStrategy(filename);
//        dataStorage.setCars(strategy.fill(maxSize));
        System.out.println("Загружено автомобилей: " + dataStorage.size());
    }

    public void handleDisplay() {
        System.out.println("\n--- Отображение данных ---");
        System.out.println(dataStorage);
    }

    public void handleBubbleSort(Comparator<Car> comparator) {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: сначала заполните данные!");
            return;
        }

        dataStorage.sort(new BubbleSortStrategy(), comparator);
    }

    public void handleNaturalSort(ToIntFunction<Car> fieldExtractor) {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: сначала заполните данные!");
            return;
        }

        dataStorage.sortWithParity(new NaturalBubbleSortForEvenStrategy(), fieldExtractor);
    }

    public void handleSaveToFile() {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: нет данных для сохранения!");
            return;
        }

        System.out.println("\n--- Сохранение в файл ---");
        String filename = inputValidator.readString("Введите имя файла для сохранения: ", false);

        try {
            Files.write(Paths.get(filename), dataStorage.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }
    }
}
