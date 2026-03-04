package org.example.data.provider;

import org.example.data.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataProviderStrategy implements DataProviderStrategy {

    String[] models = {"BMW", "Toyota", "Lada", "Mercedes", "Aurus"};

    @Override
    public List<Car> provideData(int size) {
        System.out.println("Reading data in the following format \"model\", \"wattage\", \"production year\"");
        ArrayList<Car> carData = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int wattage = rand.nextInt(100, 400);
            int productionYear = rand.nextInt(1980, 2025);
            int randomIndex = rand.nextInt(models.length);
            String model = models[randomIndex];
            Car car = new Car.Builder()
                    .setModel(model)
                    .setPower(wattage)
                    .setYear(productionYear)
                    .build();
            carData.add(car);
        }
        return carData;
    }
}
