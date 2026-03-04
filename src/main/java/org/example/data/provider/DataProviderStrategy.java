package org.example.data.provider;

import org.example.data.model.Car;

import java.util.List;

public interface DataProviderStrategy {

    List<Car> provideData(int size);
}

