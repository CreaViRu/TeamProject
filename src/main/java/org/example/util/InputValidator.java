package org.example.util;

import org.example.data.provider.Validator;

import java.util.Scanner;

public class InputValidator implements Validator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Ошибка: введите число от %d до %d\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число");
            }
        }
    }

    public String readString(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty() || allowEmpty) {
                return input;
            }
            System.out.println("Ошибка: строка не может быть пустой");
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (д/н): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("д") || input.equals("да") || input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("н") || input.equals("нет") || input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Ошибка: введите 'д' или 'н'");
        }
    }

    @Override
    public boolean validate(String source) {
        // todo logic (встроить сюда свой валидатор, чтобы он работал в парсере, который используется в Data Provider)
        return true;
    }
}