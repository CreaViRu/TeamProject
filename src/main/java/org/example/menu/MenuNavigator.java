package org.example.menu;

import org.example.menu.interfaces.MenuSwitcher;
import org.example.menu.interfaces.InputReader;
import org.example.menu.interfaces.Renderer;

import java.util.Stack;

public class MenuNavigator implements MenuSwitcher {
    private Menu currentMenu;
    private final InputReader reader;
    private final Renderer renderer;
    private final Stack<Menu> history = new Stack<>();
    private final Runnable exitHandler;

    public MenuNavigator(InputReader inputReader, Renderer renderer, Menu startMenu, Runnable exitHandler) {
        this.reader = inputReader;
        this.renderer = renderer;
        this.currentMenu = startMenu;
        this.exitHandler = exitHandler;
    }

    public void handleInput() {
        System.out.println("Select action:");
        String input = reader.get("> ");

        try {
            int choice = Integer.parseInt(input);

            if (choice < 0 || choice > currentMenu.getElementCount()) {
                System.out.println("Invalid choice");
                waitEnter();
                return;
            }

            if (choice == 0) {
                exitOrBack();
                return;
            }

            var choiceElement = currentMenu.getElement(choice - 1);
            choiceElement.select(this);
        } catch (NumberFormatException e) {
            System.out.println("Not a number entered");
        }
    }

    public void updateMenu() {
        renderer.render(currentMenu);
    }

    public void waitEnter() {
        reader.get("Press Enter to continue...");
    }

    public void switchTo(Menu menu) {
        history.push(currentMenu);
        currentMenu = menu;
    }

    private void exitOrBack() {
        if (history.isEmpty()) {
            exitHandler.run();
            return;
        }

        currentMenu = history.pop();
    }
}
