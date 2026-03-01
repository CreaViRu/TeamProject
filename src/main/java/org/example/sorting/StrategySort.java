package org.example.sorting;

import org.example.Car;
import java.util.List;

public interface StrategySort {
    void sort(List<Car> cars, String field);
}
