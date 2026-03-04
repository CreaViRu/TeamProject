package org.example;

import org.example.data.DataStorage;
import org.example.menu.Menu;
import org.example.menu.MenuNavigator;
import org.example.menu.actions.MenuActionHandler;
import org.example.menu.config.MenuConfiguration;
import org.example.menu.interfaces.InputReader;
import org.example.menu.interfaces.Renderer;
import org.example.menu.reader.ConsoleReader;
import org.example.menu.renderer.ConsoleRenderer;
import org.example.util.InputValidator;

import java.util.Scanner;

public class Application {
    private boolean isRunning = false;
    private final DataStorage dataStorage = new DataStorage();
    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator validator = new InputValidator(scanner);
    private final InputReader inputReader = new ConsoleReader(scanner);
    private final Renderer renderer = new ConsoleRenderer();
    private final MenuNavigator menuNavigator;

    public Application() {
        MenuActionHandler actionHandler = new MenuActionHandler(dataStorage, validator);
        Menu mainMenu = MenuConfiguration.createMainMenu(actionHandler);
        menuNavigator = new MenuNavigator(inputReader, renderer, mainMenu, this::stop);
    }

    public void start() {

        isRunning = true;
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

    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }
}