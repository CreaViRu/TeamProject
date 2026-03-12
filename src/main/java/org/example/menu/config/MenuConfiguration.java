package org.example.menu.config;

import org.example.data.model.Car;
import org.example.menu.Menu;
import org.example.menu.actions.MenuActionHandler;

public class MenuConfiguration {
    public static Menu createMainMenu(MenuActionHandler actions) {
        return new Menu.Builder("Car sorting")
                .addSubmenu("Filling methods", submenu -> {
                    submenu.addAction("Random filing", actions::handleRandomFill)
                            .addAction("Filling from a file", actions::handleRandomFill)
                            .addAction("Manual filling", actions::handleManualFill);
                })
                .addAction("Show data", actions::handleDisplay)
                .addSubmenu("Sorting", sortingMenu -> {
                    sortingMenu.addSubmenu("Bubble sort", (bubbleMenu) -> {
                        bubbleMenu.addAction("By default", () -> {
                                    actions.handleBubbleSort(Car.byDefault());
                                })
                                .addAction("By model", () -> {
                                    actions.handleBubbleSort(Car.byModel());
                                })
                                .addAction("By power", () -> {
                                    actions.handleBubbleSort(Car.byPower());
                                })
                                .addAction("By year", () -> {
                                    actions.handleBubbleSort(Car.byYear());
                                });
                    }).addSubmenu("Natural Bubble sort for Even", (naturalSortMenu) -> {
                        naturalSortMenu.addAction("By power", () -> {
                                    actions.handleNaturalSort(Car::getPower);
                                })
                                .addAction("By year", () -> {
                                    actions.handleNaturalSort(Car::getYear);
                                });
                    });
                })
                .addAction("Save to file", actions::handleSaveToFile)
                .build();
    }
}
