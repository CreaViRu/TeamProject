package org.example.data;

import org.example.data.model.Car;
import org.example.sorting.ParitySortStrategy;
import org.example.sorting.StrategySort;
import org.example.util.CustomArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class DataStorage {
    private final CustomArrayList<Car> cars;

    public DataStorage() {
        this.cars = new CustomArrayList<>();
    }

    public void sort(StrategySort method, Comparator<Car> comparator) {
        method.sort(cars, comparator);
    }

    public void sortWithParity(ParitySortStrategy method, ToIntFunction<Car> fieldExtractor) {
        method.sort(cars, fieldExtractor);
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addAll(List<Car> cars) {
        this.cars.addAll(cars);
    }

    public void clear() {
        System.out.println("Удалено " + cars.size() + " объектов.");
        cars.clear();
    }

    public boolean isEmpty() {
        return cars.isEmpty();
    }

    public int size() {
        return cars.size();
    }

    @Override
    public String toString() {
        if (cars.isEmpty()) {
            return "Список автомобилей пуст";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cars.size(); i++) {
            sb.append(i + 1).append(". ").append(cars.get(i)).append("\n");
        }
        return sb.toString();
    }
}