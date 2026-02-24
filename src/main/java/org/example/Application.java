package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private final Map<String, Runnable> handlers;
    private boolean isRunning;

    public Application() {
        isRunning = false;
        handlers = new HashMap<>();
    }

    public void start() {
        isRunning = true;
        logic();
    }

    public void stop() {
        isRunning = false;
    }

    public void addUserHandler(String key, Runnable handler) {
        handlers.put(key, handler);
    }

    private void logic() {
        var scanner = new Scanner(System.in);

        while (isRunning) {
            // Need to get user input
            // The scanner needs to be replaced with a custom handler
            // And the menu needs to be excluded from the loop
            System.out.println("Select action:");
            System.out.println("1. Exit");
            System.out.print("> ");

            var userInput = scanner.nextLine().trim();
            var selectedAction = handlers.get(userInput);

            if (selectedAction != null) {
                selectedAction.run();
            } else {
                System.out.println("Command \"" + userInput + "\" not found");
            }
        }
    }

    public static void main(String[] args) {
        Application app = new Application();

        app.addUserHandler("1", app::stop);
        app.start();
    }
}
