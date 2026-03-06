package org.example;

import org.example.sorting.StrategySort;

import java.util.List;

public class CarSorter {
    private final StrategySort strategy;

    public CarSorter(StrategySort strategy) {

        this.strategy = strategy;
    }

    public void sortCars(List<Car> cars, String field) {

        strategy.sort(cars, field);
    }
}
