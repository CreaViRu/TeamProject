package org.example.menu;

import org.example.menu.interfaces.MenuSwitcher;

public abstract class MenuElement {
    private final String title;

    public MenuElement(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    };

    abstract void select(MenuSwitcher switcher);
}

