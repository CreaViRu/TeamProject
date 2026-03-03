package org.example.menu.builder;

import org.example.menu.Menu;
import org.example.menu.MenuAction;

import java.util.function.Consumer;

public class MenuBuilder {
    private final Menu menu;

    public MenuBuilder(String title) {
        this(title, false);
    }

    public MenuBuilder(String title, boolean isRoot) {
        this.menu = new Menu(title, isRoot);
    }

    public MenuBuilder addSubmenu(String title, Consumer<MenuBuilder> submenuConfig) {
        MenuBuilder submenuBuilder = new MenuBuilder(title);
        submenuConfig.accept(submenuBuilder);
        menu.add(submenuBuilder.build());
        return this;
    }

    public MenuBuilder addAction(String title, Runnable action) {
        menu.add(new MenuAction(title, action));
        return this;
    }

    public Menu build() {
        return menu;
    }
}
