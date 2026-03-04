import org.example.data.model.Car;
import org.example.data.DataStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataStorageTest {
    @Test
    public void testDataStorage() {
        DataStorage storage = new DataStorage();

        assertTrue(storage.isEmpty(), "Storage must be empty");

        Car car = new Car.Builder()
                .setPower(150)
                .setModel("Test")
                .setYear(2020)
                .build();

        storage.addCar(car);

        assertEquals(1, storage.size(), "Cars must be equal to " + 1);
    }
}
