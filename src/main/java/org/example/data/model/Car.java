package org.example.data.model;

import java.util.Comparator;

public class Car implements Comparable<Car> {
    private int power;
    private String model;
    private int year;

    private Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model;
        this.year = builder.year;
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

    @Override
    public int compareTo(Car o) {

        return compareByFields(this, o, "Мощность");
    }
    
    public static int compareByFields(Car c1, Car c2, String sortField) {
        switch (sortField) {
            case "Мощность": return Integer.compare(c1.power, c2.power);
            case "Модель": return c1.model.compareTo(c2.model);
            case "Год": return Integer.compare(c1.year, c2.year);
            default: return 0;
        }
    }


    public static Comparator<Car> getComparator(String field) {

        return (c1, c2) -> compareByFields(c1, c2, field);
    }

    @Override
    public String toString() {
        return "Автомобиль (" + "Мощность = " + power +"л.с." + ", Модель = " + model  + ", Год = " + year + ')';
    }
}