import org.example.data.model.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(IllegalArgumentException.class, () -> new Car.Builder().build());
    }
}
