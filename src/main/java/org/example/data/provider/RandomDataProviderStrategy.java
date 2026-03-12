package org.example.data.provider;

import org.example.util.CustomArrayList;

import java.util.ArrayList;

public class RandomDataProviderStrategy<T> implements DataProviderStrategy<T> {

    private final Randomizer<T> randomizer;
    private final String consoleMessage;

    public RandomDataProviderStrategy(Randomizer<T> randomizer, String consoleMessage) {
        this.randomizer = randomizer;
        this.consoleMessage = consoleMessage;
    }

    @Override
    public CustomArrayList<T> provideData(int size) {
        System.out.println(consoleMessage);
        CustomArrayList<T> data = new CustomArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(randomizer.randomizeData());
        }
        return data;
    }
}
