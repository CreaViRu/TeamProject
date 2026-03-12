package org.example.data.provider;

import org.example.util.CustomArrayList;

public interface DataProviderStrategy<T> {

    CustomArrayList<T> provideData(int size);
}

