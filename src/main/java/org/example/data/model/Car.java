package org.example.data.model;

import java.util.Comparator;
import java.util.Objects;

public class Car implements Comparable<Car> {
    private final int power;
    private final String model;
    private final int year;

    private Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model;
        this.year = builder.year;
    }

    @Override
    public int compareTo(Car o) {
        Comparator<Car> comparator = Comparator.comparing(Car::getModel)
                .thenComparing(Car::getPower)
                .thenComparing(Car::getYear);
        return comparator.compare(this, o);
    }

    public static class Builder {
        private int power;
        private String model;
        private int year;

        public Builder setPower(int power) {
            this.power = power;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Car build() {

            return new Car(this);
        }
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    // метка
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return power == car.power &&
                year == car.year &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, model, year);
    }
    
    @Override
    public String toString() {
        return "Автомобиль (" + "Мощность = " + power + "л.с." + ", Модель = " + model + ", Год = " + year + ')';
    }

    public static Comparator<Car> byDefault() {
        return Comparator.naturalOrder();
    }

    public static Comparator<Car> byPower() {
        return Comparator.comparingInt(Car::getPower);
    }

    public static Comparator<Car> byModel() {
        return Comparator.comparing(Car::getModel);
    }

    public static Comparator<Car> byYear() {
        return Comparator.comparingInt(Car::getYear);
    }
}