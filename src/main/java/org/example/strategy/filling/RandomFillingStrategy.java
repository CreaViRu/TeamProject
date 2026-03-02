package org.example.strategy.filling;

import org.example.data.model.Car;
import org.example.validation.CarValidator;

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
                    .setPower(50 + random.nextInt(301))
                    .setYear(2000 + random.nextInt(24))
                    .build();


            CarValidator.standardValidator().validate(car);
            cars.add(car);
        }

        System.out.println("Сгенерировано " + size + " автомобилей");
        return cars;
    }
}