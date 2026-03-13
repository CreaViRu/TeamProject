package org.example.data.provider;

import org.example.util.CustomArrayList;

public class RandomDataProviderStrategy<T> implements DataProviderStrategy<T> {

    private final Randomizer<T> randomizer;


    public RandomDataProviderStrategy(Randomizer<T> randomizer) {
        this.randomizer = randomizer;
    }

    @Override
    public CustomArrayList<T> provideData(int size) {
        CustomArrayList<T> data = new CustomArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(randomizer.randomizeData());
        }
        return data;
    }
}
