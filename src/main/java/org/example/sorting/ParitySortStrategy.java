package org.example.sorting;

import org.example.data.model.Car;
import org.example.util.CustomArrayList;

import java.util.function.ToIntFunction;

public interface ParitySortStrategy {
    void sort(CustomArrayList<Car> data, ToIntFunction<Car> fieldExtractor);
}
