package org.example;

import org.example.data.DataStorage;
import org.example.data.model.Car;
import org.example.data.provider.*;
import org.example.util.InputValidator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application2 {

    private static final String ENTER_VEHICLE_CONSOLE_MESSAGE = "Enter vehicle information in the format \"model\", \"power\", \"year\". For example: BMW, 300, 1999";

    private boolean isRunning;
    private final DataStorage dataStorage;
    private final InputValidator validator;
    private final Scanner scanner;
    private final Map<String, Runnable> handlers;
    private final Map<String, Runnable> dataInputHandlers;
    private CommandContext commandContext;

    private final DataProviderStrategy<Car> randomData;
    private final DataProviderStrategy<Car> readingFromConsole;
    private final Parser<Car> parser;

    public Application2() {
        this.isRunning = false;
        this.scanner = new Scanner(System.in);
        handlers = new HashMap<>();


        this.validator = new InputValidator(scanner);


        this.parser = new CarParser(validator);


        this.dataStorage = new DataStorage();


        commandContext = CommandContext.APP;
        dataInputHandlers = new HashMap<>();


        Randomizer<Car> carRandomizer = new CarRandomizer(new String[]{"BMW", "Toyota", "Lada", "Mercedes", "Aurus"});


        randomData = DataProviderFactory.createRandomDataProvider(carRandomizer, ENTER_VEHICLE_CONSOLE_MESSAGE);
        readingFromConsole = DataProviderFactory.createInputDataProvider(parser, ENTER_VEHICLE_CONSOLE_MESSAGE);
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


        DataProviderStrategy<Car> fileDataProvider =
                DataProviderFactory.createFileDataProvider(fileName, parser, ENTER_VEHICLE_CONSOLE_MESSAGE);

        System.out.println(fileDataProvider.provideData(size));
        commandContext = CommandContext.APP;
    }

    public void randomData() {
        System.out.println("Random list");
        System.out.println("Enter the number from 1 to 100");
        int size = Integer.parseInt(scanner.nextLine().trim());
        System.out.println(randomData.provideData(size));
        commandContext = CommandContext.APP;
    }

    public void input() {
        System.out.println("How many cars will be in the list? Enter the number from 1 to 50");
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

    public static void main(String[] args) {
        Application2 app = new Application2();
        app.addUserHandler("1", CommandContext.APP, app::stop);
        app.addUserHandler("2", CommandContext.APP, app::enterData);
        app.addUserHandler("1", CommandContext.DATA_INPUT, app::readFromFile);
        app.addUserHandler("2", CommandContext.DATA_INPUT, app::randomData);
        app.addUserHandler("3", CommandContext.DATA_INPUT, app::input);
        app.start();
    }
}