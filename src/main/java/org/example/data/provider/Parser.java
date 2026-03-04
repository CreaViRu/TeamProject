package org.example.data.provider;

public interface Parser<T> {

    T parse(String source);
}
