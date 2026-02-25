package org.example.data;

import org.example.Car;

import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private List<Car> cars;

    public DataStorage() {
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void clear() {
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
        sb.append("\n=== Список автомобилей ===\n");
        for (int i = 0; i < cars.size(); i++) {
            sb.append(i + 1).append(". ").append(cars.get(i)).append("\n");
        }
        return sb.toString();
    }
}