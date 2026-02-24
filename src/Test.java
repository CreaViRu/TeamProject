public class Test {
    public static void main(String[] args) {
        testBuilder();
//        testSort();
//        testValidation();
    }

    static void testBuilder() {
        try {
            Car car = new Car.Builder().setPower(200).setModel("BMW").setYear(2020).build();
            System.out.println("Тест: " + car);
        } catch (Exception e) {
            System.out.println("Ошибка");
        }
    }
}