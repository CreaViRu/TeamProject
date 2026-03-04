package org.example.menu;

import org.example.menu.interfaces.MenuSwitcher;

public class MenuAction extends MenuElement {
    private final Runnable action;
    
    public MenuAction(String title, Runnable action) {
        super(title);
        this.action = action;
    }

    @Override
    public void select(MenuSwitcher switcher) {
        action.run();
    }
}
