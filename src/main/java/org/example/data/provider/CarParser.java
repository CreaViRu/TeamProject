package org.example.data.provider;

import org.example.data.model.Car;

public class CarParser implements Parser<Car> {

    private static final String ERROR_MESSAGE = "Неверный формат данных";
    private final Validator validator;

    public CarParser(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Car parse(String source) throws InvalidDataException {

        boolean isValidated = validator.validate(source);
        if (!isValidated) {
            throw new InvalidDataException(ERROR_MESSAGE);
        }


        String[] parts = source.split(",");
        String model = parts[0].trim();
        int wattage = Integer.parseInt(parts[1].trim());
        int productionYear = Integer.parseInt(parts[2].trim());

        return new Car.Builder()
                .setModel(model)
                .setPower(wattage)
                .setYear(productionYear)
                .build();
    }
}