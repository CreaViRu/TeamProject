package org.example.sorting;

import org.example.data.model.Car;

import java.util.ArrayList;
import java.util.List;

public class NaturalBubbleSortStrategy implements StrategySort {

    @Override
    public void sort(List<Car> cars, String field) {
        if (cars == null || cars.size() < 2) {
            return;
        }

        List<Integer> evenIndices = new ArrayList<>();
        List<Integer> evenValues = new ArrayList<>();

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            int value = getFieldValue(car, field);

            if (value % 2 == 0) {
                evenIndices.add(i);
                evenValues.add(value);
            }
        }

        bubbleSort(evenValues);

        for (int i = 0; i < evenIndices.size(); i++) {
            int idx = evenIndices.get(i);
            int sortedValue = evenValues.get(i);
            updateCarAtIndex(cars, idx, field, sortedValue);
        }
    }

    private int getFieldValue(Car car, String field) {
        switch (field) {
            case "Мощность":
                return car.getPower();
            case "Год":
                return car.getYear();
            default:
                throw new IllegalArgumentException("Неподдерживаемое поле: " + field);
        }
    }

    private void bubbleSort(List<Integer> list) {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private void updateCarAtIndex(List<Car> cars, int index, String field, int newValue) {
        Car oldCar = cars.get(index);
        Car.Builder builder = new Car.Builder()
                .setModel(oldCar.getModel())
                .setPower(oldCar.getPower())
                .setYear(oldCar.getYear());

        switch (field) {
            case "Мощность":
                builder.setPower(newValue);
                break;
            case "Год":
                builder.setYear(newValue);
                break;
        }

        cars.set(index, builder.build());
    }
}
