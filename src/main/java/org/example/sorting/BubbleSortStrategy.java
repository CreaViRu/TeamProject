package org.example.sorting;

import org.example.Car;
import java.util.List;

public class BubbleSortStrategy implements StrategySort {

    @Override
    public void sort(List<Car> cars, String field) {
        if (cars == null || cars.size() < 2) {
            return;
        }

        int n = cars.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                Car current = cars.get(j);
                Car next = cars.get(j + 1);

                if (Car.compareByFields(current, next, field) > 0) {
                    swap(cars, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private void swap(List<Car> list, int i, int j) {
        Car temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
