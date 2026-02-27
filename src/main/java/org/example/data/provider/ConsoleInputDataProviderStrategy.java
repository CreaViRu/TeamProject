package org.example.data.provider;

import org.example.data.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputDataProviderStrategy implements DataProviderStrategy {
    private static final int PARTS_COUNT = 3;

    @Override
    public List<Car> provideData(int size) {
        ArrayList<Car> carData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vehicle information in the format \"model\", \"power\", \"year\". For example: BMW, 300, 1999");
        String readLine = scanner.nextLine();
        String[] parts = readLine.split(",");
        for (int i = 0; i < size; i += PARTS_COUNT) {
            if (i + 2 >= parts.length) break;
            String model = parts[i].trim();
            int wattage = Integer.parseInt(parts[i + 1].trim());
            int productionYear = Integer.parseInt(parts[i + 2].trim());
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
