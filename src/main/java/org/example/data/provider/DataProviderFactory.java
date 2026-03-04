package org.example.data.provider;

public class DataProviderFactory {

    public static DataProviderStrategy createFileDataProvider(String fileName) {
        return new FileDataProviderStrategy(fileName);
    }

    public static DataProviderStrategy createInputDataProvider() {
        return new ConsoleInputDataProviderStrategy();
    }

    public static DataProviderStrategy createRandomDataProvider() {
        return new RandomDataProviderStrategy();
    }


}

