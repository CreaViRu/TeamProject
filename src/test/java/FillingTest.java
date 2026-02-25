import org.example.Car;
import org.example.strategy.filling.FileFillingStrategy;
import org.example.strategy.filling.FillingStrategy;
import org.example.strategy.filling.RandomFillingStrategy;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FillingTest {
    @Test
    public void testRandomFilling() {
        int expectedSize = 5;
        FillingStrategy strategy = new RandomFillingStrategy();
        List<Car> cars = strategy.fill(expectedSize);

        assertEquals(expectedSize, cars.size(), "Size must be equal to " + expectedSize);
    }

    @Test
    public void testFileFilling() {
        String[] expectedCars = {
                "Toyota Camry,150,2020",
                "BMW X5,300,2021",
                "Audi A4,200,2019"};

        String fileName = "test_cars.txt";
        Path path = Paths.get(fileName);

        try (var writer = Files.newBufferedWriter(path)) {
            String joinedString = String.join("\n", expectedCars);
            writer.write(joinedString);

            FillingStrategy strategy = new FileFillingStrategy(fileName);
            List<Car> cars = strategy.fill(10);

            assertEquals(expectedCars.length, cars.size(), "Size must be equal to " + expectedCars.length);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
