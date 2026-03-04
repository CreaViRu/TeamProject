package org.example.data.provider;

import java.util.List;

public interface DataProviderStrategy<T> {

    List<T> provideData(int size);
}

