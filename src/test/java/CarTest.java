import org.example.data.model.Car;
import org.example.validation.CarValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void testBuilderCreatesValidObject() {
        int expectedPower = 800;
        String expectedModel = "Car";
        int expectedYear = 1900;

        Car car = new Car.Builder()
                .setPower(expectedPower)
                .setModel(expectedModel)
                .setYear(expectedYear)
                .build();

        assertEquals(expectedPower, car.getPower(), "Power should be equal to " + expectedPower);
        assertEquals(expectedModel, car.getModel(), "Model should be equal to " + expectedModel);
        assertEquals(expectedYear, car.getYear(), "Year should be equal to " + expectedYear);
    }

    @Test
    public void testBuilderWithDefaults() {
        Car car = new Car.Builder().build();

        assertNotNull(car, "Car should be created even without parameters");
        assertNull(car.getModel(), "Model should be null");
        assertEquals(0, car.getPower(), "Power should be 0");
        assertEquals(0, car.getYear(), "Year should be 0");

        assertThrows(IllegalArgumentException.class, () -> {
            CarValidator.standardValidator().validate(car);
        }, "Validator should throw exception for invalid car");
    }

    @Test
    public void testValidatorCatchesInvalidModel() {
        Car car = new Car.Builder()
                .setModel("")
                .setPower(100)
                .setYear(2020)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            CarValidator.standardValidator().validate(car);
        }, "Empty model should cause validation error");
    }

    @Test
    public void testValidatorCatchesInvalidPower() {
        Car car = new Car.Builder()
                .setModel("BMW")
                .setPower(-10)
                .setYear(2020)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            CarValidator.standardValidator().validate(car);
        }, "Negative power should cause validation error");
    }

    @Test
    public void testValidatorCatchesInvalidYear() {
        Car car = new Car.Builder()
                .setModel("BMW")
                .setPower(100)
                .setYear(1800)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            CarValidator.standardValidator().validate(car);
        }, "Invalid year should cause validation error");
    }
}