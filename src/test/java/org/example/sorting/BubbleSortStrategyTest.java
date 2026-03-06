package org.example.sorting;

import org.example.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BubbleSortStrategyTest {

    private BubbleSortStrategy strategy;
    private List<Car> cars;

    @BeforeEach
    void setUp() {
        strategy = new BubbleSortStrategy();
        cars = new ArrayList<>();
        cars.add(new Car.Builder().setPower(300).setModel("BMW").setYear(2021).build());
        cars.add(new Car.Builder().setPower(150).setModel("Toyota").setYear(2020).build());
        cars.add(new Car.Builder().setPower(200).setModel("Audi").setYear(2019).build());
    }

    @Test
    void sortByPower() {
        strategy.sort(cars, "Мощность");

        assertEquals(150, cars.get(0).getPower());
        assertEquals(200, cars.get(1).getPower());
        assertEquals(300, cars.get(2).getPower());
    }

    @Test
    void sortByModel() {
        strategy.sort(cars, "Модель");

        assertEquals("Audi", cars.get(0).getModel());
        assertEquals("BMW", cars.get(1).getModel());
        assertEquals("Toyota", cars.get(2).getModel());
    }

    @Test
    void sortByYear() {
        strategy.sort(cars, "Год");

        assertEquals(2019, cars.get(0).getYear());
        assertEquals(2020, cars.get(1).getYear());
        assertEquals(2021, cars.get(2).getYear());
    }

    @Test
    void emptyList() {
        List<Car> empty = new ArrayList<>();
        strategy.sort(empty, "Мощность");
        assertTrue(empty.isEmpty());
    }

    @Test
    void singleElement() {
        List<Car> single = new ArrayList<>();
        single.add(new Car.Builder().setPower(100).setModel("Test").setYear(2020).build());
        strategy.sort(single, "Мощность");
        assertEquals(1, single.size());
    }

    @Test
    void noEvenValues() {
        StrategySort evenSortStrategy = new NaturalBubbleSortStrategy();

        List<Car> cars = new ArrayList<>();
        cars.add(new Car.Builder().setPower(151).setModel("A").setYear(2020).build());
        cars.add(new Car.Builder().setPower(133).setModel("B").setYear(2021).build());

        evenSortStrategy.sort(cars, "Мощность");

        assertEquals(151, cars.get(0).getPower());
        assertEquals(133, cars.get(1).getPower());
    }

    @Test
    void allEvenValues() {
        StrategySort evenSortStrategy = new NaturalBubbleSortStrategy();

        List<Car> cars = new ArrayList<>();
        cars.add(new Car.Builder().setPower(300).setModel("C").setYear(2020).build());
        cars.add(new Car.Builder().setPower(200).setModel("D").setYear(2021).build());
        cars.add(new Car.Builder().setPower(100).setModel("E").setYear(2019).build());

        evenSortStrategy.sort(cars, "Мощность");

        assertEquals(100, cars.get(0).getPower());
        assertEquals(200, cars.get(1).getPower());
        assertEquals(300, cars.get(2).getPower());
    }

}