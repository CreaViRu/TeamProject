package org.example.data.provider;

import java.util.ArrayList;

import java.util.List;

public class RandomDataProviderStrategy<T> implements DataProviderStrategy<T> {

    private final Randomizer<T> randomizer;
    private final String consoleMessage;

    public RandomDataProviderStrategy(Randomizer<T> randomizer, String consoleMessage) {
        this.randomizer = randomizer;
        this.consoleMessage = consoleMessage;
    }

    @Override
    public List<T> provideData(int size) {
        System.out.println(consoleMessage);
        ArrayList<T> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(randomizer.randomizeData());
        }
        return data;
    }
}
