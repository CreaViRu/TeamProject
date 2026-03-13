package org.example.data.provider;

public class DataProviderFactory {

    public static <T> DataProviderStrategy<T> createFileDataProvider(String fileName, Parser<T> parser) {
        return new FileDataProviderStrategy<>(fileName, parser);
    }

    public static <T> DataProviderStrategy<T> createInputDataProvider(Parser<T> parser) {
        return new ConsoleInputDataProviderStrategy<>(parser);
    }

    public static <T> DataProviderStrategy<T> createRandomDataProvider(Randomizer<T> randomizer) {
        return new RandomDataProviderStrategy<>(randomizer);
    }


}

