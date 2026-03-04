package org.example.menu;

import org.example.menu.interfaces.MenuSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Menu extends MenuElement {
    private final List<MenuElement> elements;
    private final boolean isRoot;

    private Menu(Builder builder) {
        super(builder.title);
        this.isRoot = builder.isRoot;
        this.elements = builder.elements;
    }

    public static class Builder {
        private String title;
        private final List<MenuElement> elements = new ArrayList<>();
        private boolean isRoot = false;

        public Builder(String title) {
            this(title, false);
        }

        public Builder(String title, boolean isRoot) {
            this.title = title;
            this.isRoot = isRoot;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder addSubmenu(String title, Consumer<Builder> submenuConfig) {
            Builder submenuBuilder = new Builder(title);
            submenuConfig.accept(submenuBuilder);
            elements.add(submenuBuilder.build());
            return this;
        }

        public Builder addAction(String title, Runnable action) {
            elements.add(new MenuAction(title, action));
            return this;
        }

        public Menu build() {
            return new Menu(this);
        }
    }

    @Override
    public void select(MenuSwitcher switcher) {
        switcher.switchTo(this);
    }

    public boolean isRoot() {
        return isRoot;
    }

    public List<String> getElementTitles() {
        return elements.stream().map(MenuElement::getTitle).toList();
    }

    public int getElementCount() {
        return elements.size();
    }

    public MenuElement getElement(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elements.size());
        }

        return elements.get(index);
    }
}
