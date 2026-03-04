package org.example.strategy.filling;

import org.example.data.model.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFillingStrategy implements FillingStrategy {
    private static final String[] MODELS = {
            "Toyota Camry", "BMW X5", "Mercedes C63", "Audi A4",
            "Honda Civic", "Ford Focus", "Volkswagen Golf", "Hyundai Solaris",
            "Kia Rio", "Renault Logan", "Lada Vesta", "Skoda Octavia"
    };
    private final Random random = new Random();

    @Override
    public List<Car> fill(int size) {
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Car car = new Car.Builder()
                    .setModel(MODELS[random.nextInt(MODELS.length)])
                    .setPower(50 + random.nextInt(301)) // 50-350 л.с.
                    .setYear(2000 + random.nextInt(24)) // 2000-2023
                    .build();
            cars.add(car);
        }

        System.out.println("Сгенерировано " + size + " автомобилей");
        return cars;
    }
}