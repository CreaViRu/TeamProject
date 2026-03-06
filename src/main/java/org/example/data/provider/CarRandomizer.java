package org.example.data.provider;

import org.example.data.model.Car;

import java.util.Random;

public class CarRandomizer implements Randomizer<Car> {

    private static final int MIN_WATTAGE = 100;
    private static final int MAX_WATTAGE = 400;
    private static final int MIN_PRODUCTION_YEAR = 1980;
    private static final int MAX_PRODUCTION_YEAR = 2025;

    private String[] models;

    public CarRandomizer(String[] models) {
        this.models = models;
    }

    public void setModels(String[] models) {
        this.models = models;
    }

    @Override
    public Car randomizeData() {
        Car car;
        Random rand = new Random();
        int wattage = rand.nextInt(MIN_WATTAGE, MAX_WATTAGE);
        int productionYear = rand.nextInt(MIN_PRODUCTION_YEAR, MAX_PRODUCTION_YEAR);
        int randomIndex = rand.nextInt(models.length);
        String model = models[randomIndex];
        car = new Car.Builder()
                .setModel(model)
                .setPower(wattage)
                .setYear(productionYear)
                .build();

        return car;
    }
}
