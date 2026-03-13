package org.example.sorting;

import org.example.data.model.Car;
import org.example.util.CustomArrayList;

import java.util.Collections;
import java.util.Comparator;

public class BubbleSortStrategy implements StrategySort {
    @Override
    public void sort(CustomArrayList<Car> data, Comparator<Car> comparator) {
        if (data == null || data.size() < 2) {
            return;
        }

        int n = data.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                Car current = data.get(j);
                Car next = data.get(j + 1);

                if (comparator.compare(current, next) > 0) {
                    Collections.swap(data, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
