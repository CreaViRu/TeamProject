package org.example.strategy.filling;

import org.example.Car;
import java.util.List;

public interface FillingStrategy {
    List<Car> fill(int size);
}