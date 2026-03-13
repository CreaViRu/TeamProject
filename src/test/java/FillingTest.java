import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FillingTest {
    @Test
    public void testRandomFilling() {
        int expectedSize = 5;
        //TODO
//        FillingStrategy strategy = new RandomFillingStrategy();
//        List<Car> cars = strategy.fill(expectedSize);
//
//        assertEquals(expectedSize, cars.size(), "Size must be equal to " + expectedSize);
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        //TODO
//        FillingStrategy strategy = new FileFillingStrategy(fileName);
//        List<Car> cars = strategy.fill(10);
//
//        assertEquals(expectedCars.length, cars.size(), "Size must be equal to " + expectedCars.length);
    }
}
