package org.example.menu.reader;

import org.example.menu.interfaces.InputReader;

import java.util.Scanner;

public class ConsoleReader implements InputReader {
    private final Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String get(String tip) {
        System.out.print(tip);
        return scanner.nextLine().trim().toLowerCase();
    }
}
