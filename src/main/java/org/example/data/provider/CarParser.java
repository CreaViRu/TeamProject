package org.example.data.provider;

import org.example.data.model.Car;

public class CarParser implements Parser<Car> {

    @Override
    public Car parse(String source) {
        String[] parts = source.split(",");
        String model = parts[0];
        int wattage = Integer.parseInt(parts[1].trim());
        int productionYear = Integer.parseInt(parts[2].trim());
        return new Car.Builder()
                .setModel(model)
                .setPower(wattage)
                .setYear(productionYear)
                .build();
    }
}

