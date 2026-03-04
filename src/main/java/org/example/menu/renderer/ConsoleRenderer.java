package org.example.menu.renderer;

import org.example.menu.Menu;
import org.example.menu.interfaces.Renderer;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.max;

public class ConsoleRenderer implements Renderer {
    private static final String SIDE_BORDER = "|";
    private static final String TOP_AND_DOWN_BORDER = "=";
    private static final String SEPARATOR = ". ";

    @Override
    public void render(Menu reflect) {
        var width = calcWidth(reflect);
        printTitle(reflect.getTitle(), width);
//        printGap(1, width);
        printElementTitles(reflect.getElementTitles(), width);
        printFooter(reflect, width);
//        printGap(1, width);
    }

    private static void printTitle(String title, int width) {
        var titleLength = title.length();
        printTextWithBorder(TOP_AND_DOWN_BORDER.repeat(width), width);
        printTextWithBorder(title, width);
        printTextWithBorder(TOP_AND_DOWN_BORDER.repeat(width), width);
    }

    private static void printElementTitles(List<String> elementTitles, int width) {
        IntStream.range(0, elementTitles.size())
                .mapToObj(i -> (i + 1) + SEPARATOR + elementTitles.get(i))
                .forEach(text -> printTextWithBorder(text, width));
    }

    private static void printFooter(Menu reflect, int width) {
        String text = reflect.isRoot() ? "Exit" : "Back";
        printTextWithBorder("0" + SEPARATOR + text, width);
    }

    private static void printGap(int gap, int width) {
        for (int i = 0; i < gap; i++) {
            printTextWithBorder("", width);
        }
    }

    private static int calcWidth(Menu reflect) {
        var elementTitles = reflect.getElementTitles();
        var widthLongestTitle = elementTitles
                .stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        var countElements = elementTitles.size();

        var additionalWidth = (countElements + SEPARATOR).length();

        return max(reflect.getTitle().length(), widthLongestTitle + additionalWidth);
    }

    private static void printTextWithBorder(String text, int width) {
        var padding = width - text.length();
        System.out.println(SIDE_BORDER + text + " ".repeat(padding) + SIDE_BORDER);
    }
}
