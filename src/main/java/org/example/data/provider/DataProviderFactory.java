package org.example.data.provider;

public class DataProviderFactory {

    public static <T> DataProviderStrategy<T> createFileDataProvider(String fileName, Parser<T> parser, String consoleMessage) {
        return new FileDataProviderStrategy<>(fileName, parser, consoleMessage);
    }

    public static <T> DataProviderStrategy<T> createInputDataProvider(Parser<T> parser, String consoleMessage) {
        return new ConsoleInputDataProviderStrategy<>(parser, consoleMessage);
    }

    public static <T> DataProviderStrategy<T> createRandomDataProvider(Randomizer<T> randomizer, String consoleMessage) {
        return new RandomDataProviderStrategy<>(randomizer, consoleMessage);
    }


}

