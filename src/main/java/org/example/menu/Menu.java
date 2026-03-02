package org.example.menu;

import org.example.menu.interfaces.MenuSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Menu extends MenuElement {
    private final List<MenuElement> elements = new ArrayList<>();
    private final boolean isRoot;

    public Menu(String title) {
        this(title, false);
    }

    public Menu(String title, boolean isRoot) {
        super(title);
        this.isRoot = isRoot;
    }

    @Override
    public void select(MenuSwitcher switcher) {
        switcher.switchTo(this);
    }

    public boolean isRoot() {
        return isRoot;
    }

    public MenuElement add(MenuElement menuElement) {
        elements.add(menuElement);
        return this;
    }

    public List<String> getElementTitles() {
        return elements.stream().map(MenuElement::getTitle).toList();
    }

    public int getElementCount() {
        return elements.size();
    }

    public MenuElement getElement(int index) {
        if(index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elements.size());
        }

        return elements.get(index);
    }
}
