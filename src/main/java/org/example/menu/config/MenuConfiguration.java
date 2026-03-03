package org.example.menu.config;

import org.example.menu.Menu;
import org.example.menu.actions.MenuActionHandler;
import org.example.menu.builder.MenuBuilder;

public class MenuConfiguration {
    public static Menu createMainMenu(MenuActionHandler actions) {
        return new MenuBuilder("Car sorting")
                .addSubmenu("Filling methods", submenu -> {
                    submenu.addAction("Random filing", actions::handleRandomFill)
                            .addAction("Filling from a file", actions::handleRandomFill)
                            .addAction("Manual filling", actions::handleManualFill);
                })
                .addAction("Show data", actions::handleDisplay)
//                .addAction("Sort", actions::handleSort)
                .addAction("Find car", actions::handleSearch)
                .addAction("Save to file", actions::handleSaveToFile)
                .build();
    }
}
