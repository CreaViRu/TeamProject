package org.example;

import org.example.data.DataStorage;
import org.example.menu.MenuCommand;
import org.example.sorting.BubbleSortStrategy;
import org.example.sorting.StrategySort;
import org.example.strategy.filling.*;
import org.example.util.InputValidator;

import java.util.Scanner;

public class Application {
    private boolean isRunning;
    private final DataStorage dataStorage;
    private final InputValidator validator;
    private final Scanner scanner;

    private StrategySort sortingStrategy;


    public Application() {
        this.isRunning = false;
        this.scanner = new Scanner(System.in);
        this.validator = new InputValidator(scanner);
        this.dataStorage = new DataStorage();
        this.sortingStrategy = new BubbleSortStrategy();
    }

    public void start() {
        isRunning = true;
        printWelcomeMessage();
        mainLoop();
    }

    public void stop() {
        isRunning = false;
        scanner.close();
        System.out.println("Программа завершена. До свидания!");
    }

    private void printWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("   Программа сортировки автомобилей   ");
        System.out.println("=====================================");
    }

    private void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println(MenuCommand.FILL_RANDOM.getCode() + ". " + MenuCommand.FILL_RANDOM.getDescription());
        System.out.println(MenuCommand.FILL_MANUAL.getCode() + ". " + MenuCommand.FILL_MANUAL.getDescription());
        System.out.println(MenuCommand.FILL_FILE.getCode() + ". " + MenuCommand.FILL_FILE.getDescription());
        System.out.println(MenuCommand.DISPLAY.getCode() + ". " + MenuCommand.DISPLAY.getDescription());
        System.out.println(MenuCommand.SORT_POWER.getCode() + ". " + MenuCommand.SORT_POWER.getDescription());
        System.out.println(MenuCommand.SORT_MODEL.getCode() + ". " + MenuCommand.SORT_MODEL.getDescription());
        System.out.println(MenuCommand.SORT_YEAR.getCode() + ". " + MenuCommand.SORT_YEAR.getDescription());
        System.out.println(MenuCommand.SEARCH.getCode() + ". " + MenuCommand.SEARCH.getDescription());
        System.out.println(MenuCommand.SAVE_FILE.getCode() + ". " + MenuCommand.SAVE_FILE.getDescription());
        System.out.println(MenuCommand.EXIT.getCode() + ". " + MenuCommand.EXIT.getDescription());
        System.out.print("Выберите действие > ");
    }

    private void mainLoop() {
        while (isRunning) {
            printMenu();
            String userInput = scanner.nextLine().trim();

            MenuCommand command = MenuCommand.fromCode(userInput);

            if (command == null) {
                System.out.println("Ошибка: команда \"" + userInput + "\" не найдена");
                continue;
            }

            executeCommand(command);
        }
    }

    private void executeCommand(MenuCommand command) {
        switch (command) {
            case EXIT:
                stop();
                break;

            case FILL_RANDOM:
                handleRandomFill();
                break;

            case FILL_MANUAL:
                handleManualFill();
                break;

            case FILL_FILE:
                handleFileFill();
                break;

            case DISPLAY:
                handleDisplay();
                break;

            case SORT_POWER:
                handleSort("Мощность");
                break;

            case SORT_MODEL:
                handleSort("Модель");
                break;

            case SORT_YEAR:
                handleSort("Год");
                break;

            case SEARCH:
                handleSearch();
                break;

            case SAVE_FILE:
                handleSaveToFile();
                break;
        }
    }

    private void handleRandomFill() {
        System.out.println("\n--- Случайное заполнение ---");
        int size = validator.readInt("Введите количество автомобилей [1-100]: ", 1, 100);

        FillingStrategy strategy = new RandomFillingStrategy();
        dataStorage.setCars(strategy.fill(size));

        System.out.println("Текущее количество автомобилей: " + dataStorage.size());
    }

    private void handleManualFill() {
        System.out.println("\n--- Ручное заполнение ---");
        int size = validator.readInt("Введите количество автомобилей [1-20]: ", 1, 20);

        FillingStrategy strategy = new ManualFillingStrategy(validator);
        dataStorage.setCars(strategy.fill(size));

        System.out.println("Текущее количество автомобилей: " + dataStorage.size());
    }

    private void handleFileFill() {
        System.out.println("\n--- Заполнение из файла ---");
        String filename = validator.readString("Введите имя файла: ", false);
        int maxSize = validator.readInt("Максимальное количество для загрузки [1-100]: ", 1, 100);

        FillingStrategy strategy = new FileFillingStrategy(filename);
        dataStorage.setCars(strategy.fill(maxSize));

        System.out.println("Загружено автомобилей: " + dataStorage.size());
    }

    private void handleDisplay() {
        System.out.println("\n--- Отображение данных ---");
        System.out.println(dataStorage);
    }

    private void handleSort(String field) {
        if (dataStorage.getCars().isEmpty()) {
            System.out.println("Ошибка: сначала заполните данные!");
            return;
        }
        System.out.println("\n--- Сортировка по " + field + " ---");
        sortingStrategy.sort(dataStorage.getCars(), field);
        System.out.println("Данные отсортированы:");
        for (Car car : dataStorage.getCars()) {
            System.out.println(car);
        }
    }

    private void handleSearch() {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: сначала заполните данные!");
            return;
        }

        System.out.println("\n--- Поиск автомобиля ---");

        System.out.println("Функция поиска будет добавлена позже");
    }

    private void handleSaveToFile() {
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: нет данных для сохранения!");
            return;
        }

        System.out.println("\n--- Сохранение в файл ---");
        String filename = validator.readString("Введите имя файла для сохранения: ", false);
        boolean append = validator.readYesNo("Добавить к существующему файлу?");


        System.out.println("Функция сохранения будет добавлена позже");
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }
}