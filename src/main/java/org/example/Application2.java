package org.example;

import org.example.data.DataStorage;
import org.example.data.provider.CommandContext;
import org.example.data.provider.DataProviderFactory;
import org.example.data.provider.DataProviderStrategy;
import org.example.util.InputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application2 {
    private boolean isRunning;
    private final DataStorage dataStorage;
    private final InputValidator validator;
    private final Scanner scanner;
    private final Map<String, Runnable> handlers;
    private final Map<String, Runnable> dataInputHandlers;
    private CommandContext commandContext;
    private DataProviderStrategy fileDataProviderStrategy;
    private final DataProviderStrategy randomData;
    private final DataProviderStrategy readingFromConsole;

    public Application2() {
        this.isRunning = false;
        this.scanner = new Scanner(System.in);
        handlers = new HashMap<>();
        this.validator = new InputValidator(scanner);
        this.dataStorage = new DataStorage();
        commandContext = CommandContext.APP;
        dataInputHandlers = new HashMap<>();
        randomData = DataProviderFactory.createRandomDataProvider();
        readingFromConsole = DataProviderFactory.createInputDataProvider();
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

    public void enterData() {
        commandContext = CommandContext.DATA_INPUT;
    }

    public void readFromFile() {
        System.out.println("Enter the file name");
        String fileName = scanner.nextLine().trim();
        System.out.println("Enter the number from 1 to 3");
        int size = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Reading from file");
        DataProviderStrategy fileDataProvider = DataProviderFactory.createFileDataProvider(fileName);
        System.out.println(fileDataProvider.provideData(size));
        commandContext = CommandContext.APP;
    }

    public void randomData() {
        System.out.println("Random list");
        System.out.println("Enter the number from 1 to 3");
        int size = Integer.parseInt(scanner.nextLine().trim());
        System.out.println(randomData.provideData(size));
        commandContext = CommandContext.APP;
    }

    public void input() {
        System.out.println("How many cars will be in the list? Enter the number from 1 to 3");
        int size = Integer.parseInt(scanner.nextLine().trim());
        System.out.println(readingFromConsole.provideData(size));
        commandContext = CommandContext.APP;
    }

    public void addUserHandler(String key, CommandContext commandContext, Runnable handler) {
        switch (commandContext) {
            case APP -> handlers.put(key, handler);
            case DATA_INPUT -> dataInputHandlers.put(key, handler);
        }
    }

    private void printWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("   Программа сортировки автомобилей   ");
        System.out.println("=====================================");
    }

    private void mainLoop() {
        while (isRunning) {
            showCommands();
            String userInput = scanner.nextLine().trim();
            Runnable selectedAction = null;
            switch (commandContext) {
                case APP -> selectedAction = handlers.get(userInput);
                case DATA_INPUT -> selectedAction = dataInputHandlers.get(userInput);
            }
            if (selectedAction != null) {
                selectedAction.run();
            } else {
                System.out.println("Command \"" + userInput + "\" not found");
            }
        }
    }

    private void showCommands() {
        switch (commandContext) {
            case APP -> {
                System.out.println("Select action:");
                System.out.println("1. Exit");
                System.out.println("2. Enter data");
            }
            case DATA_INPUT ->
                    System.out.println("Select a data entry method:\n1. From file\n2. Random\n3. Manual entry");
        }
        System.out.print("> ");
    }

//    private void executeCommand(MenuCommand command) {
//        switch (command) {
//            case EXIT:
//                stop();
//                break;
//
//            case FILL_RANDOM:
//                handleRandomFill();
//                break;
//
//            case FILL_MANUAL:
//                handleManualFill();
//                break;
//
//            case FILL_FILE:
//                handleFileFill();
//                break;
//
//            case DISPLAY:
//                handleDisplay();
//                break;
//
//            case SORT_POWER:
//                handleSort("Мощность");
//                break;
//
//            case SORT_MODEL:
//                handleSort("Модель");
//                break;
//
//            case SORT_YEAR:
//                handleSort("Год");
//                break;
//
//            case SEARCH:
//                handleSearch();
//                break;
//
//            case SAVE_FILE:
//                handleSaveToFile();
//                break;
//        }
//    }
//
//    private void handleRandomFill() {
//        System.out.println("\n--- Случайное заполнение ---");
//        int size = validator.readInt("Введите количество автомобилей [1-100]: ", 1, 100);
//
//        FillingStrategy strategy = new RandomFillingStrategy();
//        dataStorage.setCars(strategy.fill(size));
//
//        System.out.println("Текущее количество автомобилей: " + dataStorage.size());
//    }
//
//    private void handleManualFill() {
//        System.out.println("\n--- Ручное заполнение ---");
//        int size = validator.readInt("Введите количество автомобилей [1-20]: ", 1, 20);
//
//        FillingStrategy strategy = new ManualFillingStrategy(validator);
//        dataStorage.setCars(strategy.fill(size));
//
//        System.out.println("Текущее количество автомобилей: " + dataStorage.size());
//    }
//
//    private void handleFileFill() {
//        System.out.println("\n--- Заполнение из файла ---");
//        String filename = validator.readString("Введите имя файла: ", false);
//        int maxSize = validator.readInt("Максимальное количество для загрузки [1-100]: ", 1, 100);
//
//        FillingStrategy strategy = new FileFillingStrategy(filename);
//        dataStorage.setCars(strategy.fill(maxSize));
//
//        System.out.println("Загружено автомобилей: " + dataStorage.size());
//    }

//    private void handleDisplay() {
//        System.out.println("\n--- Отображение данных ---");
//        System.out.println(dataStorage);
//    }
//
//    private void handleSort(String field) {
//        if (dataStorage.isEmpty()) {
//            System.out.println("Ошибка: сначала заполните данные!");
//            return;
//        }
//
//        System.out.println("\n--- Сортировка по " + field + " ---");
//
//        System.out.println("Функция сортировки будет добавлена позже");
//    }
//
//    private void handleSearch() {
//        if (dataStorage.isEmpty()) {
//            System.out.println("Ошибка: сначала заполните данные!");
//            return;
//        }
//
//        System.out.println("\n--- Поиск автомобиля ---");
//
//        System.out.println("Функция поиска будет добавлена позже");
//    }
//
//    private void handleSaveToFile() {
//        if (dataStorage.isEmpty()) {
//            System.out.println("Ошибка: нет данных для сохранения!");
//            return;
//        }
//
//        System.out.println("\n--- Сохранение в файл ---");
//        String filename = validator.readString("Введите имя файла для сохранения: ", false);
//        boolean append = validator.readYesNo("Добавить к существующему файлу?");
//
//
//        System.out.println("Функция сохранения будет добавлена позже");
//    }

    public static void main(String[] args) {
        Application2 app = new Application2();
        app.addUserHandler("1", CommandContext.APP, app::stop);
        app.addUserHandler("2", CommandContext.APP, app::enterData);
        app.addUserHandler("1", CommandContext.DATA_INPUT, app::readFromFile);
        app.addUserHandler("2", CommandContext.DATA_INPUT, app::randomData);
        app.addUserHandler("3", CommandContext.DATA_INPUT, app::input);
        app.start();
        app.start();
    }
}