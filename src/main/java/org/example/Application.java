package org.example;

import org.example.data.DataStorage;
import org.example.menu.Menu;
import org.example.menu.MenuAction;
import org.example.menu.MenuCommand;
import org.example.menu.MenuNavigator;
import org.example.menu.interfaces.InputReader;
import org.example.menu.interfaces.Renderer;
import org.example.menu.reader.ConsoleReader;
import org.example.menu.renderer.ConsoleRenderer;
import org.example.strategy.filling.*;
import org.example.util.InputValidator;

import java.util.Scanner;

public class Application {
    private boolean isRunning = false;
    private final DataStorage dataStorage = new DataStorage();
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator = new InputValidator(scanner);
    private final InputReader inputReader = new ConsoleReader(scanner);
    private final Renderer renderer = new ConsoleRenderer();
    private final MenuNavigator menuNavigator = new MenuNavigator(inputReader, renderer, initMenu(), this::stop);


    public Menu initMenu() {
        var mainMenu = new Menu("Sorting program", true);
        var fillingMenu = new Menu("Filling method");

        mainMenu.add(fillingMenu);
        mainMenu.add(new MenuAction("Show data", this::handleDisplay));
//        mainMenu.add(new MenuAction("Sort", this::handleSort)); // Not implemented

        fillingMenu.add(new MenuAction("Random", this::handleRandomFill));
        fillingMenu.add(new MenuAction("File", this::handleFileFill));
        fillingMenu.add(new MenuAction("Manual", this::handleManualFill));

        return mainMenu;
    }

    public void start() {
        isRunning = true;
//        printWelcomeMessage();
        mainLoop();
    }

    public void stop() {
        isRunning = false;
        scanner.close();
    }

    private void mainLoop() {
        while (isRunning) {
            menuNavigator.updateMenu();
            menuNavigator.handleInput();
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
        if (dataStorage.isEmpty()) {
            System.out.println("Ошибка: сначала заполните данные!");
            return;
        }
        System.out.println("\n--- Сортировка по " + field + " ---");
        System.out.println("Функция сортировки будет добавлена позже");
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