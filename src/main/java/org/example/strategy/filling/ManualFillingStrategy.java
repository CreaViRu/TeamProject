// package org.example.strategy.filling;

// import org.example.data.model.Car;
// import org.example.util.InputValidator;
// import java.util.ArrayList;
// import java.util.List;

// public class ManualFillingStrategy implements FillingStrategy {
//     private final InputValidator validator;

//     public ManualFillingStrategy(InputValidator validator) {
//         this.validator = validator;
//     }

//     @Override
//     public List<Car> fill(int size) {
//         List<Car> cars = new ArrayList<>();

//         System.out.println("\n=== Ввод данных для " + size + " автомобилей ===");

//         for (int i = 0; i < size; i++) {
//             System.out.println("\nАвтомобиль #" + (i + 1));

//             while (true) {
//                 try {
//                     String model = validator.readString("Модель: ", false);
//                     int power = validator.readInt("Мощность (л.с.) [1-2000]: ", 1, 2000);
//                     int year = validator.readInt("Год выпуска [1886-2025]: ", 1886, 2025);

//                     Car car = new Car.Builder()
//                             .setModel(model)
//                             .setPower(power)
//                             .setYear(year)
//                             .build();

//                     cars.add(car);
//                     break;

//                 } catch (IllegalArgumentException e) {
//                     System.out.println("Ошибка валидации: " + e.getMessage());
//                     System.out.println("Попробуйте снова:");
//                 }
//             }
//         }

//         System.out.println("\nУспешно введено " + size + " автомобилей");
//         return cars;
//     }
// }

package org.example.strategy.filling;

import org.example.custom_collection.CustomArrayList;
import org.example.data.model.Car;
import org.example.util.InputValidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManualFillingStrategy implements FillingStrategy {

    private final InputValidator validator;

    public ManualFillingStrategy(InputValidator validator) {
        this.validator = validator;
    }

    @Override
    public List<Car> fill(int size) {

        return IntStream.range(0, size)
                .mapToObj(i -> readCar())
                .collect(Collectors.toCollection(CustomArrayList::new));
    }

    private Car readCar() {

        String model = validator.readString("Введите модель: ", false);
        int year = validator.readInt("Введите год: ", 1900, 2025);
        int power = validator.readInt("Введите мощность: ", 50, 2000);

        return new Car.Builder()
                .setModel(model)
                .setYear(year)
                .setPower(power)
                .build();
    }
}