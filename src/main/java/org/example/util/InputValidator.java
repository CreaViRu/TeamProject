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
        try {
            String[] parts = source.split(",");

            if (parts.length != 3) {
                System.out.println("Ошибка: неверный формат. Нужно: Модель,Мощность,Год");
                return false;
            }

            String model = parts[0].trim();
            if (model.isEmpty()) {
                System.out.println("Ошибка: модель не может быть пустой");
                return false;
            }
            if (model.length() > 50) {
                System.out.println("Ошибка: модель слишком длинная (макс. 50 символов)");
                return false;
            }

            try {
                int power = Integer.parseInt(parts[1].trim());
                if (power <= 0 || power > 2000) {
                    System.out.println("Ошибка: мощность должна быть от 1 до 2000 л.с.");
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: мощность должна быть числом");
                return false;
            }

            try {
                int year = Integer.parseInt(parts[2].trim());
                int currentYear = java.time.Year.now().getValue();
                if (year < 1886 || year > currentYear + 1) {
                    System.out.println("Ошибка: год должен быть от 1886 до " + (currentYear + 1));
                    return false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: год должен быть числом");
                return false;
            }

            return true;

        } catch (Exception e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
            return false;
        }
    }
}