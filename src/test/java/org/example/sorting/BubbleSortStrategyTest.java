package org.example.sorting;

import org.example.data.model.Car;
import org.example.util.CustomArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BubbleSortStrategyTest {

    private BubbleSortStrategy strategy;
    private CustomArrayList<Car> cars;

    @BeforeEach
    void setUp() {
        strategy = new BubbleSortStrategy();
        cars = new CustomArrayList<>();
        cars.add(new Car.Builder().setPower(300).setModel("BMW").setYear(2021).build());
        cars.add(new Car.Builder().setPower(150).setModel("Toyota").setYear(2020).build());
        cars.add(new Car.Builder().setPower(200).setModel("Audi").setYear(2019).build());
    }

    @Test
    void sortByPower() {
        strategy.sort(cars, Car.byPower());

        assertEquals(150, cars.get(0).getPower());
        assertEquals(200, cars.get(1).getPower());
        assertEquals(300, cars.get(2).getPower());
    }

    @Test
    void sortByModel() {
        strategy.sort(cars, Car.byModel());

        assertEquals("Audi", cars.get(0).getModel());
        assertEquals("BMW", cars.get(1).getModel());
        assertEquals("Toyota", cars.get(2).getModel());
    }

    @Test
    void sortByYear() {
        strategy.sort(cars, Car.byYear());

        assertEquals(2019, cars.get(0).getYear());
        assertEquals(2020, cars.get(1).getYear());
        assertEquals(2021, cars.get(2).getYear());
    }

    @Test
    void emptyList() {
        CustomArrayList<Car> empty = new CustomArrayList<>();
        strategy.sort(empty, Car.byYear());
        assertTrue(empty.isEmpty());
    }

    @Test
    void singleElement() {
        CustomArrayList<Car> single = new CustomArrayList<>();
        single.add(new Car.Builder().setPower(100).setModel("Test").setYear(2020).build());
        strategy.sort(single, Car.byYear());
        assertEquals(1, single.size());
    }

    @Test
    void noEvenValues() {
        ParitySortStrategy evenSortStrategy = new NaturalBubbleSortForEvenStrategy();
        CustomArrayList<Car> cars = new CustomArrayList<>();

        cars.add(new Car.Builder().setPower(151).setModel("A").setYear(2020).build());
        cars.add(new Car.Builder().setPower(133).setModel("B").setYear(2021).build());

        evenSortStrategy.sort(cars, Car::getPower);

        assertEquals(151, cars.get(0).getPower());
        assertEquals(133, cars.get(1).getPower());
    }

    @Test
    void allEvenValues() {
        ParitySortStrategy evenSortStrategy = new NaturalBubbleSortForEvenStrategy();
        CustomArrayList<Car> cars = new CustomArrayList<>();

        cars.add(new Car.Builder().setModel("A").setPower(300).setYear(2020).build());
        cars.add(new Car.Builder().setModel("A").setPower(200).setYear(2021).build());
        cars.add(new Car.Builder().setModel("A").setPower(100).setYear(2019).build());

        evenSortStrategy.sort(cars, Car::getPower);

        assertEquals(100, cars.get(0).getPower());
        assertEquals(200, cars.get(1).getPower());
        assertEquals(300, cars.get(2).getPower());
    }

}