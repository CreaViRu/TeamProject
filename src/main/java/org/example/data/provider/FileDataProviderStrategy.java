package org.example.data.provider;

import org.example.data.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataProviderStrategy implements DataProviderStrategy {
    private final String filePath;

    public FileDataProviderStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Car> provideData(int size) {
        ArrayList<Car> carData = new ArrayList<>();
        try {
            System.out.println("Reading data in the following format \"model\", \"wattage\", \"production year\"");
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filePath));
            String line;
            String[] parts;
            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split(",");
                String model = parts[0];
                int wattage = Integer.parseInt(parts[1].trim());
                int productionYear = Integer.parseInt(parts[2].trim());
                Car car = new Car.Builder()
                        .setModel(model)
                        .setPower(wattage)
                        .setYear(productionYear)
                        .build();
                carData.add(car);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return carData.subList(0, size);
    }
}
