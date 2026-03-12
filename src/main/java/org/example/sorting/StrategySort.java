package org.example.sorting;

import org.example.data.model.Car;
import org.example.util.CustomArrayList;

import java.util.Comparator;

public interface StrategySort {
    void sort(CustomArrayList<Car> data, Comparator<Car> comparator);
}
