package org.example.menu.actions;

import org.example.data.DataStorage;
import org.example.util.InputValidator;

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

    public void handleSort(String field) {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: сначала заполните данные!");
            return;
        }
        System.out.println("\n--- Сортировка по " + field + " ---");
        System.out.println("Функция сортировки будет добавлена позже");
    }

    public void handleSearch() {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: сначала заполните данные!");
            return;
        }
        System.out.println("\n--- Поиск автомобиля ---");
        System.out.println("Функция поиска будет добавлена позже");
    }

    public void handleSaveToFile() {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: нет данных для сохранения!");
            return;
        }
        System.out.println("\n--- Сохранение в файл ---");
        String filename = inputValidator.readString("Введите имя файла для сохранения: ", false);
        boolean append = inputValidator.readYesNo("Добавить к существующему файлу?");
        System.out.println("Функция сохранения будет добавлена позже");
    }
}
