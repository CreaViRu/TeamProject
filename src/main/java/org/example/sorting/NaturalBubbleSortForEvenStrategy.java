package org.example.sorting;

import org.example.data.model.Car;
import org.example.util.CustomArrayList;

import java.util.Comparator;
import java.util.function.ToIntFunction;

public class NaturalBubbleSortForEvenStrategy implements ParitySortStrategy {
    @Override
    public void sort(CustomArrayList<Car> data, ToIntFunction<Car> fieldExtractor) {
        if (data == null || data.size() < 2) {
            return;
        }

        CustomArrayList<Integer> evenIndices = new CustomArrayList<>();
        CustomArrayList<Car> evenValues = new CustomArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            Car item = data.get(i);
            var value = fieldExtractor.applyAsInt(item);

            if (value % 2 == 0) {
                evenIndices.add(i);
                evenValues.add(item);
            }
        }
        BubbleSortStrategy bubbleSortStrategy = new BubbleSortStrategy();
        bubbleSortStrategy.sort(evenValues, Comparator.naturalOrder());

        for (int i = 0; i < evenIndices.size(); i++) {
            data.set(evenIndices.get(i), evenValues.get(i));
        }
    }
}
